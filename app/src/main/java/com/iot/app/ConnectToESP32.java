package com.iot.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.thanosfisherman.wifiutils.WifiUtils;
import com.thanosfisherman.wifiutils.wifiConnect.ConnectionErrorCode;
import com.thanosfisherman.wifiutils.wifiConnect.ConnectionSuccessListener;
import com.thanosfisherman.wifiutils.wifiDisconnect.DisconnectionErrorCode;
import com.thanosfisherman.wifiutils.wifiDisconnect.DisconnectionSuccessListener;

import java.util.List;

public class ConnectToESP32 extends AppCompatActivity {
    ListView wifiList;
    EditText editTextPassword;
    Button buttonConnect;
    Button buttonScanAgain;
    String wifiName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_connect_to_esp32);

        //init the views
        wifiList = findViewById(R.id.wifiList);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonConnect = findViewById(R.id.buttonConnect);
        buttonScanAgain = findViewById(R.id.buttonScanAgain);

        //add the click listener to the buttons
        buttonConnect.setOnClickListener(v -> onConnectToWifi());
        buttonScanAgain.setOnClickListener(v -> scanWifi());

        //disconnect from the current wifi
        if(WifiUtils.withContext(this).isWifiConnected()){
            WifiUtils.withContext(this)
                    .disconnect(disconnectionSuccessListener);
        }
        wifiList.setOnItemClickListener((adapterView, view, i, l) -> wifiName = wifiList.getAdapter().getItem(i).toString());

        //init the wifi scanning
        scanWifi();

    }

    private void scanWifi(){
        WifiUtils.withContext(this).enableWifi(this::checkResult);
        WifiUtils.withContext(this).scanWifi(this::getScanResults).start();
    }


    private void getScanResults(@NonNull final List<ScanResult> results) {
        if (results.isEmpty()) {
            Toast.makeText(this, "NO DEVICE DETECTED", Toast.LENGTH_SHORT).show();
            Log.i("WIFI", "SCAN RESULTS IT'S EMPTY");
            return;
        }
        String[] detectedWifi = new String[results.size()];
        for (int i = 0; i < results.size(); i++) {
            detectedWifi[i] = results.get(i).SSID;

        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, detectedWifi);
        wifiList.setAdapter(adapter);
        Log.i("WIFI", "GOT SCAN RESULTS " + results);
    }

    private void checkResult(boolean isSuccess) {
        if (isSuccess)
            Toast.makeText(this, "WIFI ENABLED", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "COULDN'T ENABLE WIFI", Toast.LENGTH_SHORT).show();
    }
    private final DisconnectionSuccessListener disconnectionSuccessListener=  new  DisconnectionSuccessListener() {
        @Override
        public void success() {
            Toast.makeText(ConnectToESP32.this, "Disconnect success!", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void failed(@NonNull DisconnectionErrorCode errorCode) {
            Toast.makeText(ConnectToESP32.this, "Failed to disconnect: " + errorCode, Toast.LENGTH_SHORT).show();
        }
    };


    private final ConnectionSuccessListener connectionSuccessListener = new ConnectionSuccessListener() {

        @Override
        public void success() {
            findViewById(R.id.loadingPanel).setVisibility(View.INVISIBLE);
            Intent intent = new Intent(ConnectToESP32.this, SendPassToESP32Activity.class);
            Toast.makeText(ConnectToESP32.this, "SUCCESS!", Toast.LENGTH_SHORT).show();
            ConnectToESP32.this.startActivity(intent);
        }

        @Override
        public void failed(@NonNull ConnectionErrorCode errorCode) {
            findViewById(R.id.loadingPanel).setVisibility(View.INVISIBLE);
            Toast.makeText(ConnectToESP32.this, "EPIC FAIL!" + errorCode, Toast.LENGTH_SHORT).show();
        }
    };
    private void onConnectToWifi() {
        View view = this.getCurrentFocus();
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

        findViewById(R.id.loadingPanel).setVisibility(View.VISIBLE);

        String password = editTextPassword.getText().toString();

        WifiUtils.withContext(this)
                .connectWith(wifiName, password)
                .onConnectionResult(connectionSuccessListener)
                .start();
    }

}