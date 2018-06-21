package com.example.francine.capital;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    String[] estado = new String[5];
    String[] capital = new String[5];

    Random r;
    TextView est, resposta;
    int sorteado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        est = (TextView)findViewById(R.id.estado);
        resposta = (TextView)findViewById(R.id.resultado);

        estado[0] = "Paraná";
        capital[0] = "Curitiba";
        estado[1] = "Goiás";
        capital[1] = "Goiania";
        estado[2] = "Piaí";
        capital[2] = "Teresina";
        estado[3] = "Sergipe";
        capital[3] = "Aracaju";
        estado[4] = "Tocantins";
        capital[4] = "Palmas";

        r = new Random();
        sorteado = r.nextInt(5);

        est.setText(String.valueOf(estado[sorteado]));
    }

    public void verificaResposta(View view){
        EditText cap = (EditText)findViewById(R.id.capital);

        if(cap.length()!=0){
            if(cap.getText().toString().equals(capital[sorteado]))
                resposta.setText(String.valueOf("ACERTOU!!"));
            else
                resposta.setText(String.valueOf("ERROU!!"));
        }
        else{
            Toast msg = Toast.makeText(this, "Preencha a capital", Toast.LENGTH_SHORT);
            msg.show();
        }


    }
}
