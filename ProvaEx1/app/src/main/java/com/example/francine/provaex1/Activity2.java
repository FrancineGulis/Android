package com.example.francine.provaex1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        TextView media = (TextView)findViewById(R.id.media);
        TextView freq = (TextView)findViewById(R.id.freq);
        TextView condi = (TextView)findViewById(R.id.condi);


        Intent it = getIntent();
        if(it != null) {
            Bundle params = it.getExtras();
            if(params != null){
                int n1 = params.getInt("n1");
                int n2 = params.getInt("n2");
                int frequencia = params.getInt("freq");

                float med = (n1 + n2)/2.0f;
                String condicao = " ";

                if((med > 7 || med == 7) && (frequencia > 75 || frequencia == 75))
                    condicao = "Aprovado";

                if( ((med > 4 || med == 4) && (med < 6.9 || med == 6.9)) && (frequencia > 75 || frequencia == 75))
                    condicao = "Final";

                if(med < 4)
                    condicao = "Reprovado por Nota";

                if(frequencia < 75)
                condicao = "Reprovado por Falta";

                media.setText(String.valueOf(med));
                freq.setText(String.valueOf(frequencia));
                condi.setText(condicao);
            }
        }
    }
}
