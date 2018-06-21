package com.example.francine.tempclient;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    EditText input;
    EditText output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input = (EditText)findViewById(R.id.input);
        output = (EditText)findViewById(R.id.output);
    }

    public void calc(View view){
        float inputTemp = Float.parseFloat(input.getText().toString());
        Conversor objConversor = new Conversor(inputTemp, handler);
        objConversor.start();
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            super.handleMessage(msg);

            if(msg.what == 1){
                float tempF = msg.getData().getFloat("resultado");
                output.setText(Float.toString(tempF));
            }
        }
    };
}

