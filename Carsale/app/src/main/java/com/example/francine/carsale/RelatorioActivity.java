package com.example.francine.carsale;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Francine on 06/11/2017.
 */

public class RelatorioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorio);

        Intent it = getIntent();
        double valorVendido = 0;
        Bundle vendido;

        TextView valor = (TextView)findViewById(R.id.valor);

        if(it != null){
            vendido = it.getBundleExtra("vendido");
            valorVendido = vendido.getDouble("vendido");
            String s = String.valueOf(valorVendido);
            s = s.replaceAll("\\.",",");
            valor.setText("R$ "+s);
        }else
            valor.setText("Nenhum valor");
    }
}
