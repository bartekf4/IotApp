package com.iot.app;

import androidx.appcompat.app.AppCompatActivity;
//✨
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.amplifyframework.core.Amplify;
import com.iot.app.database.Database;

import java.util.List;


public class DashboardActivity extends AppCompatActivity {

    TextView textViewWelcome;
    TextView latest;
    ListView listViewLatest;

    Button buttonSignOut;
    Button buttonRefresh;

    Database db = new Database();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        textViewWelcome = findViewById(R.id.textViewWelcome);
        latest = findViewById(R.id.textViewLatest);
        listViewLatest = findViewById(R.id.listViewLatest);
        buttonRefresh = findViewById(R.id.buttonRefresh);
        buttonSignOut = findViewById(R.id.buttonSignOut);

        buttonRefresh.setOnClickListener(v -> setData());
        buttonSignOut.setOnClickListener(v -> {
                    Amplify.Auth.signOut(
                            (x) -> {
                                Log.i("AuthQuickstart", "Signed out successfully");
                                startActivity(new Intent(DashboardActivity.this, MainActivity.class));
                            });
                });

        Amplify.Auth.getCurrentUser(
                user -> {
                    textViewWelcome.setText("Welcome " + user.getUsername() + "!");
                },
                error -> Log.e("AuthQuickstart", error.toString())
        );


    }

    public void setData(){
        List<String> data =db.getLights();
        latest.setText(data.get(0));

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
        listViewLatest.setAdapter(adapter);

    }
}