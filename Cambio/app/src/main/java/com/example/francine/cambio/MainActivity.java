package com.example.francine.cambio;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText input;
    TextView output;
    Button convD, convE, convL, convP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input = (EditText)findViewById(R.id.real);
        output = (TextView) findViewById(R.id.resultado);
        convD = (Button)findViewById(R.id.Dolar);
        convE = (Button)findViewById(R.id.Euro);
        convL = (Button)findViewById(R.id.Libra);
        convP = (Button)findViewById(R.id.Peso);
    }

    public void converte(View view){
        float real = Float.parseFloat(input.getText().toString());
        int moeda = 0;
        if(view.getId() == R.id.Dolar)
            moeda = 1;
        else if(view.getId() == R.id.Euro)
            moeda = 2;
        else if(view.getId() == R.id.Libra)
            moeda = 3;
        else
            moeda = 4;
        Conversor objConversor = new Conversor(real, moeda, handler);
        objConversor.start();
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            super.handleMessage(msg);

            if(msg.what == 1){
                float resultado = msg.getData().getFloat("resultado");
                output.setText(Float.toString(resultado));
            }
        }
    };
}
