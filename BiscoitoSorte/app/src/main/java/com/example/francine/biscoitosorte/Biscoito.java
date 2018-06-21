package com.example.francine.biscoitosorte;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

public class Biscoito extends AppCompatActivity {
    SeekBar seek;
    TextView sorte,prog;
    String[] frases;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biscoito);

        seek = (SeekBar)findViewById(R.id.sFrase);
        sorte = (TextView)findViewById(R.id.frase);
        prog = (TextView)findViewById(R.id.txBar);

        frases = new String[]{"A vida trará coisas boas se tiveres paciência.","Demonstre amor e alegria em todas as oportunidades e verás que a paz nasce dentro de você.",
        "Não compense na ira o que lhe falta na razão.", "Defeitos e virtudes são apenas dois lados da mesma moeda.", "A maior de todas as torres começa no solo."};

        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                sorte.setText(frases[progress]);
                prog.setText(String.valueOf(progress+1));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar){

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar){

            }
        });
    }
}
