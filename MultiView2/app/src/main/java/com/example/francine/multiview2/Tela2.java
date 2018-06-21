package com.example.francine.multiview2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Tela2 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela2);

        TextView nome = (TextView)findViewById(R.id.nome);
        TextView idade = (TextView)findViewById(R.id.idade);
        TextView telefone = (TextView)findViewById(R.id.telefone);
        TextView login = (TextView)findViewById(R.id.login);
        TextView senha = (TextView)findViewById(R.id.senha);

        Intent it = getIntent();
        if(it != null) {
            Bundle params = it.getExtras();
            if(params != null){
                String msg1 = params.getString("nome");
                int msg2 = params.getInt("idade");
                int msg3 = params.getInt("telefone");
                String msg4 = params.getString("login");
                String msg5 = params.getString("senha");

                nome.setText(msg1);
                idade.setText(String.valueOf(msg2));
                telefone.setText(String.valueOf(msg3));
                login.setText(msg4);
                senha.setText(msg5);
            }
        }
    }

}
