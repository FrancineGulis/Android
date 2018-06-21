package com.example.francine.provaex2;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText real;
    TextView dolar;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        real = (EditText)findViewById(R.id.real);
        dolar = (TextView) findViewById(R.id.dolar);
        button = (Button)findViewById(R.id.converte);
    }

    public void converte(View view){
        float r = Float.parseFloat(real.getText().toString());

        Conversor objConversor = new Conversor(r,handler);
        objConversor.start();
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            super.handleMessage(msg);

            if(msg.what == 1){
                float resultado = msg.getData().getFloat("resultado");
                dolar.setText(Float.toString(resultado));
            }
        }
    };
}
