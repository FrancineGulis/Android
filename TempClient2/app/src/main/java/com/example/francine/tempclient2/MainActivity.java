package com.example.francine.tempclient2;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    EditText input;
    EditText output;
    Button convC, convF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input = (EditText)findViewById(R.id.input);
        output = (EditText)findViewById(R.id.output);
        convC = (Button)findViewById(R.id.coverterC);
        convF = (Button)findViewById(R.id.converterF);
    }

    public void calc(View view){
        float inputTemp = Float.parseFloat(input.getText().toString());
        int escala = 0;
        if(view.getId() == R.id.converterF)
            escala = 1;
        else if(view.getId() == R.id.coverterC)
            escala = 2;
        Conversor objConversor = new Conversor(inputTemp, escala, handler);
        objConversor.start();
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            super.handleMessage(msg);

            if(msg.what == 1){
                float tempO = msg.getData().getFloat("resultado");
                output.setText(Float.toString(tempO));
            }
        }
    };
}
