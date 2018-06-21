package com.example.francine.cronometro;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int cnt = 0;
    Button start, stop;
    TextView counter;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        counter = (TextView)findViewById(R.id.counter);
        start = (Button) findViewById(R.id.Start);
        stop = (Button) findViewById(R.id.Stop);

        start.setEnabled(true);
        stop.setEnabled(false);

        start.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                handler.post(timedTask);
                start.setEnabled(false);
                stop.setEnabled(true);

            }
        });

        stop.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                handler.removeCallbacks(timedTask);
                start.setEnabled(true);
                stop.setEnabled(false);
                cnt = 0;
            }
        });
    }

    private Runnable timedTask = new Runnable() {
        @Override
        public void run() {
            cnt++;
            counter.setText(String.valueOf(cnt));
            handler.postDelayed(timedTask, 1000);
        }
    };
}


