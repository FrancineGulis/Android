package com.example.francine.churrascometro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    SeekBar sbMan, sbWoman, sbKid;
    TextView tvMan, tvWoman, tvKid, outputSausage, outputMeat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sbMan = (SeekBar)findViewById(R.id.seekBarMan);
        sbWoman = (SeekBar)findViewById(R.id.seekBarWoman);
        sbKid = (SeekBar)findViewById(R.id.seekBarKid);
        tvMan = (TextView)findViewById(R.id.tvMan);
        tvWoman = (TextView)findViewById(R.id.tvWoman);
        tvKid = (TextView)findViewById(R.id.tvKid);
        outputSausage = (TextView)findViewById(R.id.ling);
        outputMeat = (TextView)findViewById(R.id.carne);

        outputSausage.setText("");
        outputMeat.setText("");
        tvMan.setText("0");
        tvWoman.setText("0");
        tvKid.setText("0");


        sbMan.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                tvMan.setText(String.valueOf(progress));
                calculate(progress, sbWoman.getProgress(), sbKid.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar){

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar){

            }
        });

        sbWoman.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                tvWoman.setText(String.valueOf(progress));
                calculate(sbMan.getProgress(), progress, sbKid.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar){

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar){

            }
        });

        sbKid.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                tvKid.setText(String.valueOf(progress));
                calculate(sbMan.getProgress(), sbWoman.getProgress(), progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar){
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar){
            }
        });
    }

    public void calculate(float man, float woman, float kid){

        outputSausage.setText(String.valueOf(((man*250+woman*200+kid*100)/1000)+"Kg"));
        outputMeat.setText(String.valueOf((man*500+woman*300+kid*200)/1000+"Kg"));
    }
}
