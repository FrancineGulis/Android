package com.example.francine.oscarapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Francine on 14/11/2017.
 */

public class FilmeDetalhes extends AppCompatActivity {
    public static String filmeVotado = null;
    private int filmeId = -1;
    private ArrayList<Filme> filmeList;
    private Filme f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filme_detalhes);

        Intent it = getIntent();

        ImageView imagem = (ImageView) findViewById(R.id.imageView);
        TextView nome = (TextView) findViewById(R.id.nome);
        TextView genero = (TextView) findViewById(R.id.genero);

        if (it != null) {
            Bundle args = it.getBundleExtra("args");
            filmeId = args.getInt("filmeId");
            filmeList = (ArrayList<Filme>)args.getSerializable("ARRAYLIST");
            f = filmeList.get(filmeId);
        }

        if (filmeId == -1) {
            nome.setText("");
            genero.setText("");
            Toast.makeText(this, "Filme não encontrado", Toast.LENGTH_LONG).show();
        }
        else {
            Bitmap bitmap = FilmeAdapter.decodeBase64(f.getImagem());

            imagem.setImageBitmap(bitmap);
            nome.setText(f.getNome());
            genero.setText(f.getGenero());
        }
    }

    public void votar(View view) {

        if(filmeVotado == null){
            Toast.makeText(this, "Voto bem sucedido: " + f.getNome(), Toast.LENGTH_LONG).show();
            filmeVotado = f.getNome();
        }
        else{
            Toast.makeText(this, "Voto alterado: " + f.getNome(), Toast.LENGTH_LONG).show();
            filmeVotado = f.getNome();
        }

        finish();
    }

}
