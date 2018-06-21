package com.example.francine.splashscreen2;

import android.app.ProgressDialog;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void prog(View view){
        final ProgressDialog mprogressDialog = ProgressDialog.show(MainActivity.this, "Aguarde", "Processando");

        Handler handler = new Handler();

        Runnable task = new Runnable() {
            @Override
            public void run() {
                mprogressDialog.dismiss();
            }
        };

        handler.postDelayed(task,3000);
    }
}