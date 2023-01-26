package com.iot.app.database;

import android.util.Log;

import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.UserDevice;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Database {
    public final static String TAG = "database";
    public URL url;

    public Database() {
        try {
            url =  new URL("https://3lhicw5dsl.execute-api.eu-central-1.amazonaws.com/default/light");
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        }

    }


    public String fetchLights() {

        try {

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            int status = con.getResponseCode();
            Log.d("status", "onCreate: " + status);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            Log.d("status", "onCreate: " + content);
            in.close();
            con.disconnect();
            return content.toString();

        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public List<String> getLights() {
        String content = fetchLights();
        List<String> lights = new ArrayList<>();
        try {
            JSONObject json = new JSONObject(content);
            JSONArray jsonArray = json.getJSONArray("Items");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String timestamp = jsonObject.getJSONObject("timestamp")
                        .getString("S");


                String hum = jsonObject.getJSONObject("payload")
                        .getJSONObject("M")
                        .getJSONObject("message")
                        .getString("S");

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String date = sdf.format(new Date(Long.parseLong(timestamp)));
                System.out.println(hum + " " + date);

                lights.add("Light: " + hum + " " + date);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        Collections.reverse(lights);
        return lights;
    }


}
