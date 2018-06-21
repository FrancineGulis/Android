package com.example.francine.carros;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ModeloH extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modelo_f);

        int modeloId = 3;
        Intent it = getIntent();

        String[] modelo = new String[]{"City", "Civic", "HRV"};
        float[] valor = new float[]{32000.00f, 65000.00f, 100000.00f};
        Integer[] imageId = {R.drawable.city, R.drawable.civic, R.drawable.hrv};

        TextView carName = (TextView)findViewById(R.id.carName);
        TextView v = (TextView)findViewById(R.id.valor);
        ImageView img = (ImageView)findViewById(R.id.imageView);

        if(it!=null)
            modeloId = it.getIntExtra("modeloId",3);

        if(modeloId == 3){
            carName.setText("");
            v.setText("");
            Toast.makeText(this,"Modelo não encontrado", Toast.LENGTH_LONG).show();
        }
        else{
            carName.setText(modelo[modeloId]);
            v.setText(String.valueOf(valor[modeloId]));
            img.setImageResource(imageId[modeloId]);
        }
    }
}