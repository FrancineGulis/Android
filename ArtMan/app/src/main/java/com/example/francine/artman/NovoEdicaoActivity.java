package com.example.francine.artman;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

public class NovoEdicaoActivity extends AppCompatActivity {

    private EditText edtNome;
    private EditText edtRevista;
    private EditText edtEdicao;
    private Spinner spEstado;
    private CheckBox cbPago;
    private Artigo artigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_edicao);

        edtNome = (EditText)findViewById(R.id.edtNome);
        edtRevista = (EditText)findViewById(R.id.edtRevista);
        edtEdicao = (EditText)findViewById(R.id.edtEdicao);
        spEstado = (Spinner)findViewById(R.id.spEstado);
        cbPago = (CheckBox)findViewById(R.id.cbPago);

        Intent intent = getIntent();
        if(intent != null){
            artigo = (Artigo)intent.getSerializableExtra("artigo");
            if(artigo!=null){
                edtNome.setText(artigo.nome);
                edtRevista.setText(artigo.revista);
                edtEdicao.setText(artigo.edicao);
                spEstado.setSelection(artigo.status);
                cbPago.setChecked(artigo.pago==1?true: false);
            }
        }
    }

    public void salvar(View v){
        BancoDeDados db = new BancoDeDados(this);
        db.abrir();
        if(artigo != null){
            db.atualizaArtigo(artigo.id, edtNome.getText().toString(),
                    edtRevista.getText().toString(), edtEdicao.getText().toString(),
                    spEstado.getSelectedItemPosition(), cbPago.isChecked()?1:0);
        }else {
            db.insereArtigo(edtNome.getText().toString(), edtRevista.getText().toString(),
                    edtEdicao.getText().toString(), spEstado.getSelectedItemPosition(),
                    cbPago.isChecked()?1:0);
        }
        db.fechar();
        setResult(ArtigosActivity.REQUEST_SALVOU);
        finish();
    }

}
