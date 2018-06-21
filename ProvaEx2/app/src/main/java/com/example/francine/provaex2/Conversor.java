package com.example.francine.provaex2;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

/**
 * Created by Francine on 25/09/2017.
 */

public class Conversor extends Thread{
    private float real, dolar;

    private Handler handler;

    public Conversor(float real, Handler handler){
        this.real = real;
        this.handler = handler;
    }

    @Override
    public void run(){
        super.run();

        dolar = real/3.25f;

        Bundle b = new Bundle();
        b.putFloat("resultado", dolar);

        Message msg = new Message();
        msg.what = 1;
        msg.setData(b);
        handler.sendMessage(msg);
    }

}
