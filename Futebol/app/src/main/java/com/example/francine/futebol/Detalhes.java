package com.example.francine.futebol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Detalhes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        int timeId = 3;
        Intent it = getIntent();

        String[] time = {"Atlético", "Coritiba", "Paraná"};
        String[] cidade = {"Curitiba", "Curitiba", "Curitiba"};
        String[] estado = {"Paraná", "Paraná", "Paraná"};
        String[] titulos = {"Campeão Paranaence \n"+"Fita Azul Internacional\n"+"Campeão Brasieliro Série B \n",
                "Campeão Paranaence\n"+"Copa Paraná\n"+"Campeão Brasieliro\n",
                "Campeão Paranaence \n" + "Torneio da morte \n" + "Campeão Brasieliro Série B \n"};

        TextView nome = (TextView)findViewById(R.id.nome);
        TextView cid = (TextView)findViewById(R.id.cidade);
        TextView est = (TextView)findViewById(R.id.estado);
        TextView tit = (TextView)findViewById(R.id.titulo);

        if(it!=null)
            timeId = it.getIntExtra("timeId",3);

        if(timeId == 3){
            nome.setText("");
            cid.setText("");
            est.setText("");
            tit.setText("");
            Toast.makeText(this,"Time não encontrado", Toast.LENGTH_LONG).show();
        }
        else{
           nome.setText(time[timeId]);
            cid.setText(cidade[timeId]);
            est.setText(estado[timeId]);
            tit.setText(titulos[timeId]);
        }
    }
}
