package com.example.francine.provaex1;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText n1, n2, freq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        n1 = (EditText)findViewById(R.id.n1);
        n2 = (EditText)findViewById(R.id.n2);
        freq = (EditText)findViewById(R.id.freq);
    }

    public void onClick(View view){
        Intent it = new Intent(this, Activity2.class);
        Bundle params = new Bundle();

        if(n1.length()==0){
            Toast.makeText(this, "Forneça a nota 1!", Toast.LENGTH_LONG).show();
            return;
        }
        if(n2.length()==0){
            Toast.makeText(this, "Forneça a nota 2!", Toast.LENGTH_LONG).show();
            return;
        }
        if(freq.length()==0){
            Toast.makeText(this, "Forneça a frequência!", Toast.LENGTH_LONG).show();
            return;
        }

        params.putInt("n1", Integer.parseInt(n1.getText().toString()));
        params.putInt("n2", Integer.parseInt(n2.getText().toString()));
        params.putInt("freq", Integer.parseInt(freq.getText().toString()));

        it.putExtras(params);
        startActivity(it);
    }
}
