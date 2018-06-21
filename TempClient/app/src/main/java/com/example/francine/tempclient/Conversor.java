package com.example.francine.tempclient;

import android.os.Bundle;
        import android.os.Handler;
        import android.os.Message;

        import java.io.DataInputStream;
        import java.io.DataOutputStream;
        import java.io.IOException;
        import java.net.Socket;


public class Conversor extends Thread{
    private float tempC, tempF;
    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;
    private static final String IP = "172.20.161.215";
    private static final int porta = 12345;

    private Handler handler;

    public Conversor(float tempC, Handler handler){
        this.tempC = tempC;
        this.handler = handler;
    }

    @Override
    public void run(){
        super.run();
        try{
            socket = new Socket(IP, porta);
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());
            out.writeFloat(tempC);
            out.flush();
            tempF = in.readFloat();
            out.close();
            in.close();
            socket.close();
            Bundle b = new Bundle();
            b.putFloat("resultado", tempF);
            Message msg = new Message();
            msg.what = 1;
            msg.setData(b);
            handler.sendMessage(msg);

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}