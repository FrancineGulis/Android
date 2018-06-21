package com.example.francine.multiview2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Tela1 extends Activity {
    EditText nome, idade, telefone, login, senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela1);

        nome = (EditText)findViewById(R.id.nome);
        idade = (EditText)findViewById(R.id.idade);
        telefone = (EditText)findViewById(R.id.telefone);
        login = (EditText)findViewById(R.id.login);
        senha = (EditText)findViewById(R.id.senha);
    }

    public void onClick(View view){
        Intent it = new Intent(this, Tela2.class);
        Bundle params = new Bundle();

        if(nome.length()==0){
            Toast.makeText(this, "Forneça seu nome!", Toast.LENGTH_LONG).show();
            return;
        }
        if(idade.length()==0){
            Toast.makeText(this, "Forneça sua idade!", Toast.LENGTH_LONG).show();
            return;
        }
        if(telefone.length()==0){
            Toast.makeText(this, "Forneça seu telefone!", Toast.LENGTH_LONG).show();
            return;
        }
        if(login.length()==0){
            Toast.makeText(this, "Forneça seu login!", Toast.LENGTH_LONG).show();
            return;
        }
        if(senha.length()==0){
            Toast.makeText(this, "Forneça sua senha!", Toast.LENGTH_LONG).show();
            return;
        }

        params.putString("nome",nome.getText().toString());
        params.putInt("idade", Integer.parseInt(idade.getText().toString()));
        params.putInt("telefone", Integer.parseInt(telefone.getText().toString()));
        params.putString("login",login.getText().toString());
        params.putString("senha",senha.getText().toString());
        it.putExtras(params);
        startActivity(it);
    }
}
