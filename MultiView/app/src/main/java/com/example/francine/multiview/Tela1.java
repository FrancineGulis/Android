package com.example.francine.multiview;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Tela1 extends Activity {
    EditText nome1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela1);

        nome1 = (EditText)findViewById(R.id.nome);
    }

    public void onClick(View view){
        Intent it = new Intent(this, Tela2.class);

        if(nome1.length()==0){
            Toast.makeText(this, "Forneça seu nome!", Toast.LENGTH_LONG).show();
            return;
        }

        it.putExtra("msg", nome1.getText().toString());
        startActivity(it);
    }
}
