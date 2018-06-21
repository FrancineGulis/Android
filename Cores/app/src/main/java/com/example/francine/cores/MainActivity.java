package com.example.francine.cores;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private SeekBar redSeekBar;
    private SeekBar greenSeekBar;
    private SeekBar blueSeekBar;
    private TextView selectedColorTextView;
    private TextView color;
    private String[] hexcolor = {"00","00","00"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        redSeekBar = (SeekBar)findViewById(R.id.redSeekBar);
        greenSeekBar = (SeekBar)findViewById(R.id.greenSeekBar);
        blueSeekBar = (SeekBar)findViewById(R.id.blueSeekBar);

        redSeekBar.setOnSeekBarChangeListener(new EventSeek((byte)0));
        greenSeekBar.setOnSeekBarChangeListener(new EventSeek((byte)1));
        blueSeekBar.setOnSeekBarChangeListener(new EventSeek((byte)2));

        selectedColorTextView = (TextView) findViewById(R.id.selectedColor);
        color= (TextView) findViewById(R.id.color);

        setColor("#" + hexcolor[0] + hexcolor[1] + hexcolor[2]);
    }

    private void setColor(String str){
        selectedColorTextView.setText(str);
        color.setBackgroundColor(Color.parseColor(str));
    }

    private void setHexNumber(int progress, byte color){
        String c = Integer.toHexString(progress);
        hexcolor[color] = (c.length() == 2 ? "" : "0")+c;
        setColor("#" + hexcolor[0] + hexcolor[1] + hexcolor[2]);
    }

    private class EventSeek implements SeekBar.OnSeekBarChangeListener {
        private byte color;
        public EventSeek(byte color){
            this.color=color;
        }

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            setHexNumber(progress,color);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };
}




