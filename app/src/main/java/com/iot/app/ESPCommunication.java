package com.iot.app;

import android.util.Log;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import javax.websocket.*;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

@ClientEndpoint
public class ESPCommunication {
    private static final String TAG = "ESPCommunication";
    //TODO: Change this URL to ESP address
    private static final String URL = "ws://connect.websocket.in/v3/1?api_key=VCXCEuvhGcBDP7XhiJJUDvR1e1D3eiVjgZ9VRiaV&notify_self";

    OkHttpClient client;
    Request request;
    WebSocket webSocket;

    private final WebSocketListener listener = new WebSocketListener() {

        @Override
        public void onOpen(WebSocket webSocket, Response response) {
            Log.d(TAG, "onOpen: " + response);
        }

        @Override
        public void onMessage(WebSocket webSocket, String text) {
            Log.d(TAG, "onMessage: " + text);
        }

        @Override
        public void onMessage(WebSocket webSocket, ByteString bytes) {
            Log.d(TAG, "onMessage: " + bytes.toString());
        }

        @Override
        public void onClosed(WebSocket webSocket, int code, String reason) {
            Log.d(TAG, "onClosed: " + reason);
        }

        @Override
        public void onFailure(WebSocket webSocket, Throwable t, Response response) {
            Log.d(TAG, "onFailure: " + response);
        }
    };


    public ESPCommunication() {
        client = new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();
        request = new Request.Builder().url(URL).build();
        webSocket = client.newWebSocket(request, listener);
    }

    public void sendMessage(String message) {
        webSocket.send(message);
        Log.d(TAG, "sendMessage: " + message);
    }
}
