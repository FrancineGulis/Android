package com.example.francine.splashscreen2;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class SplashScreen extends Activity implements Runnable{

    private  final int DELAY = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Toast.makeText(this, "Aguarde o carregamento deste Aplicativo...", Toast.LENGTH_SHORT).show();

        Handler h = new Handler();
        h.postDelayed(this,DELAY);
    }

    @Override
    public void run(){
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}