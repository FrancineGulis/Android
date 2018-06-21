package com.example.francine.cambio;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class Conversor extends Thread{
    private float real, moedaO;
    private int moeda;
    private Socket socket;
    private DataOutputStream outV;
    private DataOutputStream outM;
    private DataInputStream in;
    private static final String IP = "172.20.161.215";
    private static final int porta = 12347;

    private Handler handler;

    public Conversor(float real, int moeda, Handler handler){
        this.real = real;
        this.moeda = moeda;
        this.handler = handler;
    }

    @Override
    public void run(){
        super.run();
        try{
            socket = new Socket(IP, porta);
            outV = new DataOutputStream(socket.getOutputStream());
            outM = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());

            outV.writeFloat(real);
            outV.flush();
            outM.writeInt(moeda);
            outM.flush();

            moedaO = in.readFloat();

            outV.close();
            outM.close();
            in.close();
            socket.close();

            Bundle b = new Bundle();
            b.putFloat("resultado", moedaO);

            Message msg = new Message();
            msg.what = 1;
            msg.setData(b);
            handler.sendMessage(msg);

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}