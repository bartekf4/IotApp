package com.iot.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.wifi.ScanResult;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.thanosfisherman.wifiutils.WifiUtils;
import com.thanosfisherman.wifiutils.wifiDisconnect.DisconnectionErrorCode;
import com.thanosfisherman.wifiutils.wifiDisconnect.DisconnectionSuccessListener;


import java.util.List;


public class SendPassToESP32Activity extends AppCompatActivity {

    ListView wifiList;
    EditText editTextPassword;
    Button buttonPair;
    Button buttonScanAgain;
    String wifiName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_send_pass_to_esp32);

        //init the views
        wifiList = findViewById(R.id.wifiList1);
        editTextPassword = findViewById(R.id.editTextPassword1);
        buttonPair = findViewById(R.id.buttonPair);
        buttonScanAgain = findViewById(R.id.buttonScanAgain1);

        //add the click listener to the buttons
        buttonPair.setOnClickListener(v -> onPair());
        buttonScanAgain.setOnClickListener(v -> scanWifi());


        wifiList.setOnItemClickListener((adapterView, view, i, l) -> wifiName = wifiList.getAdapter().getItem(i).toString());

        //init the wifi scanning
        scanWifi();
    }

    private void scanWifi() {
        WifiUtils.withContext(this).enableWifi(this::checkResult);
        WifiUtils.withContext(this).scanWifi(this::getScanResults).start();
    }

    private void onPair() {
        ESPCommunication espCommunication = new ESPCommunication();
        if (wifiName == null || wifiName.isEmpty()) {
            Toast.makeText(this, "PLEASE SELECT A WIFI", Toast.LENGTH_SHORT).show();
            return;
        }
        espCommunication.sendMessage(String.format("%s,%s", wifiName, editTextPassword.getText().toString()));
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

}