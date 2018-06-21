package com.example.francine.multiview;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Tela2 extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela2);

        TextView saida = (TextView)findViewById(R.id.msg);

        Intent it = getIntent();
        if(it != null) {
            String msg = it.getStringExtra("msg");
            saida.setText("Bem vindo " + msg);
        }
    }

    public void onClick(View view){
        Intent it = new Intent(this, Tela1.class);
        startActivity(it);
    }
}
