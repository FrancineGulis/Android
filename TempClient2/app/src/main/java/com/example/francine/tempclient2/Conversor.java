package com.example.francine.tempclient2;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class Conversor extends Thread{
    private float tempI, tempO;
    private int escala;
    private Socket socket;
    private DataOutputStream outT;
    private DataOutputStream outE;
    private DataInputStream in;
    private static final String IP = "192.168.100.6";
    private static final int porta = 12346;

    private Handler handler;

    public Conversor(float tempI, int escala, Handler handler){
        this.tempI = tempI;
        this.escala = escala;
        this.handler = handler;
    }

    @Override
    public void run(){
        super.run();
        try{
            socket = new Socket(IP, porta);
            outT = new DataOutputStream(socket.getOutputStream());
            outE = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());

            outT.writeFloat(tempI);
            outT.flush();
            outE.writeInt(escala);
            outE.flush();

            tempO = in.readFloat();

            outT.close();
            outE.close();
            in.close();
            socket.close();

            Bundle b = new Bundle();
            b.putFloat("resultado", tempO);

            Message msg = new Message();
            msg.what = 1;
            msg.setData(b);
            handler.sendMessage(msg);

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}