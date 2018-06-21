package com.example.francine.oscarapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DiretorDetalhes extends AppCompatActivity {
    public static String diretorVotado = null;
    private int diretorId = -1;
    private ArrayList<Diretor> diretorList;
    private Diretor d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diretor_detalhes);

        Intent it = getIntent();

        TextView nome = (TextView) findViewById(R.id.nome);

        if (it != null) {
            Bundle args = it.getBundleExtra("args");
            diretorId = args.getInt("diretorId");
            diretorList = (ArrayList<Diretor>)args.getSerializable("ARRAYLIST");
            d = diretorList.get(diretorId);
        }

        if (diretorId == -1) {
            nome.setText("");
            Toast.makeText(this, "Diretor não encontrado", Toast.LENGTH_LONG).show();
        }
        else {
            nome.setText(d.getNome());
        }
    }

    public void votar(View view) {
        if(diretorVotado == null){
            Toast.makeText(this, "Voto bem sucedido: " + d.getNome(), Toast.LENGTH_LONG).show();
            diretorVotado = d.getNome();
        }
        else{
            Toast.makeText(this, "Voto alterado: " + d.getNome(), Toast.LENGTH_LONG).show();
            diretorVotado = d.getNome();
        }

        finish();
    }
}
