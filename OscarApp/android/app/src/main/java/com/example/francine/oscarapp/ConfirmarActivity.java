package com.example.francine.oscarapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import static android.view.View.INVISIBLE;

public class ConfirmarActivity extends AppCompatActivity {

    private TextView filme;
    private TextView diretor;
    private TextView txt;
    private Button mFilme;
    private Button mDiretor;
    private String vFilme;
    private String vDiretor;
    public static boolean confirmação = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar);

        filme = (TextView)findViewById(R.id.fvotado);
        diretor = (TextView)findViewById(R.id.diretor);
        txt = (TextView)findViewById(R.id.textView10);
        mFilme = (Button)findViewById(R.id.button3);
        mDiretor = (Button)findViewById(R.id.button4);

        vFilme = FilmeDetalhes.filmeVotado;
        vDiretor = DiretorDetalhes.diretorVotado;

        if(vFilme!=null)
            filme.setText(vFilme);
        else
            filme.setText("");

        if(vDiretor!=null)
            diretor.setText(vDiretor);
        else
            diretor.setText("");

        if(confirmação == true){
            txt.setText("Votação encerrada!!");
            mFilme.setVisibility(INVISIBLE);
            mDiretor.setVisibility(INVISIBLE);
        }

    }


    public void mudarVotoFilme(View view){
        Intent i = new Intent(this, VotarFilmeActivity.class);
        startActivity(i);
        finish();
    }
    public void mudarVotoDiretor(View view){
        Intent i = new Intent(this, VotarDiretorActivity.class);
        startActivity(i);
        finish();
    }

    public void confirmar(View view){
        if((vFilme==null)||(vDiretor==null))
            Toast.makeText(this, "Vote em todas as categorias!", Toast.LENGTH_LONG).show();
        else {
            if (confirmação == false) {
                Intent intent = new Intent(ConfirmarActivity.this, GravarVoto.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Seu voto já foi computado!!", Toast.LENGTH_LONG).show();
            }
        }
    }
}
