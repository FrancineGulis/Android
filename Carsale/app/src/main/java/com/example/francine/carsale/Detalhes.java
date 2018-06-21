package com.example.francine.carsale;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class Detalhes extends AppCompatActivity {
    private int carroId= -1;
    private ArrayList<Carro> carList;
    private Carro c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        Intent it = getIntent();

        ImageView imagem = (ImageView)findViewById(R.id.imageView);
        TextView modelo = (TextView)findViewById(R.id.modelo);
        TextView fabricante = (TextView)findViewById(R.id.fabricante);
        TextView ano = (TextView)findViewById(R.id.ano);
        TextView cor = (TextView)findViewById(R.id.cor);
        TextView preco = (TextView)findViewById(R.id.preco);

        if(it!=null){
            Bundle args = it.getBundleExtra("args");
            carroId = args.getInt("carroId");
            carList = (ArrayList<Carro>)args.getSerializable("ARRAYLIST");
            c = carList.get(carroId);
        }

        if(carroId == -1){
            modelo.setText("");
            fabricante.setText("");
            ano.setText("");
            cor.setText("");
            preco.setText("");
            Toast.makeText(this,"Carro não encontrado", Toast.LENGTH_LONG).show();
        }
        else{
            Bitmap bitmap = SplashScreen.decodeBase64(c.getImagem());

            imagem.setImageBitmap(bitmap);
            modelo.setText(c.getModelo());
            fabricante.setText(c.getFabricante());
            ano.setText(c.getAno());
            cor.setText(c.getCor());
            preco.setText(c.getPreco());
        }
    }

    public void comprar (View view){
        Toast.makeText(this,"Sucesso, " + c.getModelo() +" vendido!!", Toast.LENGTH_SHORT).show();

        Intent i = new Intent();

        Bundle vendido = new Bundle();
        vendido.putString("preco", c.getPreco());
        vendido.putInt("carroId", carroId);
        vendido.putString("nome", c.getModelo());
        i.putExtra("vendido",vendido);

        setResult(1, i);
        finish();
    }
}
