package com.example.francine.asynctask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView tex;
    private Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tex = (TextView)findViewById(R.id.tex);
        bt = (Button)findViewById(R.id.bt);
    }

    public void processamento (View view){
        NumTask num = new NumTask(tex,bt);
        //Executa o doInBackground e passa o valor 50 como parâmetro
        num.execute(50);
    }
}
