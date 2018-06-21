package com.example.francine.threadconcorrente;

import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ProgressBar progress;
    private TextView text;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progress = (ProgressBar)findViewById(R.id.progressBar1);
        text = (TextView)findViewById(R.id.textView1);
    }

    public void startProgress(View view){

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for(int i=0; i<=10; i++){
                    final int value = i;
                    doFakeWork();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            text.setText("Updating");
                            progress.setProgress(value);
                        }
                    });
                }
            }
        };
        new Thread(runnable).start();
    }

    private void doFakeWork(){
        SystemClock.sleep(2000);
    }
}
