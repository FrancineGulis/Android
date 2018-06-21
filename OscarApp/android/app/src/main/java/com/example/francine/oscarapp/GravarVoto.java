package com.example.francine.oscarapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

public class GravarVoto extends AppCompatActivity implements Response.Listener,
        Response.ErrorListener{

    public static final String REQUEST_TAG = "OscarApp";
    private TextView mTextView;
    private RequestQueue mQueue;
    private String usuario;
    private String mensagem, filme, diretor;
    public static String u;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gravar_voto);

        mTextView = (TextView)findViewById(R.id.menssagem);

        usuario=u;
        filme = FilmeDetalhes.filmeVotado;
        filme = filme.replaceAll(" ","_");

        diretor = DiretorDetalhes.diretorVotado;
        diretor = diretor.replaceAll(" ","_");

       // mButton = (Button)findViewById(R.id.button2);
    }

    @Override
    protected void onStart(){
        super.onStart();

        mQueue = CustomVolleyRequestQueue.getmInstance(this.getApplicationContext())
                .getRequestQueue();

        //mButton.setOnClickListener(new View.OnClickListener(){
            //@Override
            //public void onClick(View v){
        new AlertDialog.Builder(this)
                .setTitle("Confirmando voto")
                .setMessage("Filme: "+ FilmeDetalhes.filmeVotado +"\nDiretor: " + DiretorDetalhes.diretorVotado)
                .setPositiveButton("sim",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                String url = "http://192.168.43.207:8080/OscarApp/Votar?user=" + usuario + "&filme=" + filme + "&diretor=" + diretor;
                                final CustomJSONObjectRequest jsonRequest = new CustomJSONObjectRequest(Request.Method
                                        .GET, url, new JSONObject(), GravarVoto.this, GravarVoto.this);
                                jsonRequest.setTag(REQUEST_TAG);

                                mQueue.add(jsonRequest);
                            }
                        })
                .setNegativeButton("não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(GravarVoto.this, ConfirmarActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }).show();

            //}
        //});
    }

    @Override
    protected void onStop(){
        super.onStop();
        if (mQueue != null){
            mQueue.cancelAll(REQUEST_TAG);
        }
    }

    @Override
    public void onErrorResponse(VolleyError error){mTextView.setText(error.getMessage());
    }

    @Override
    public void onResponse(Object response) {

        try {
            mensagem = ((JSONObject) response).getString("message");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (mensagem.equals("Voto mal sucedido")) {
            mTextView.setText(mensagem);
            finish();
        } else {
            ConfirmarActivity.confirmação = true;
            Toast.makeText(GravarVoto.this, "Voto confirmado!!", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, ConfirmarActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
