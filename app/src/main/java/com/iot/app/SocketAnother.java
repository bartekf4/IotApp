package com.iot.app;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketAnother {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    public final static int port = 80;
    private final static String URL = "192.168.4.1";


    public void startConnection( ) throws IOException {
        clientSocket = new Socket(URL, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public String sendMessage(String msg) throws IOException {
        Log.d("WebSocketTest", "sendMessage: " + msg);
        return in.readLine();
    }

    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }

}
