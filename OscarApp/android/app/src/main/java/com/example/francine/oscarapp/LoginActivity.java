package com.example.francine.oscarapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;


public class LoginActivity extends AppCompatActivity implements Response.Listener,
        Response.ErrorListener{

    public static final String REQUEST_TAG = "OscarApp";
    private EditText uEditText;
    private EditText sEditText;
    private TextView mTextView;
    private Button mButton;
    private RequestQueue mQueue;
    private String senha;
    private String mensagem, filme, diretor;
    public  String usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        uEditText = (EditText) findViewById(R.id.usuario);
        sEditText = (EditText) findViewById(R.id.senha);
        mTextView = (TextView)findViewById(R.id.menssagem);
        mButton = (Button)findViewById(R.id.btlogin);

    }

    @Override
    protected void onStart(){
        super.onStart();

        mQueue = CustomVolleyRequestQueue.getmInstance(this.getApplicationContext())
                .getRequestQueue();

        mButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                usuario = uEditText.getText().toString();
                senha = sEditText.getText().toString();

                System.out.println("U: "+GravarVoto.u);
                System.out.println("Usuario: "+usuario);

                String url = "http://192.168.43.207:8080/OscarApp/UserValidator?login=" + usuario + "&senha=" + senha;
                final CustomJSONObjectRequest jsonRequest = new CustomJSONObjectRequest(Request.Method
                        .GET, url, new JSONObject(), LoginActivity.this, LoginActivity.this);
                jsonRequest.setTag(REQUEST_TAG);

                mQueue.add(jsonRequest);
            }
        });
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
            filme = ((JSONObject) response).getString("filme");
            diretor = ((JSONObject) response).getString("diretor");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (mensagem.equals("Login incorreto")) {
            mTextView.setText(mensagem);
            super.onStart();
        } else {
            if (filme.equals("v")) {
                ConfirmarActivity.confirmação = false;
                FilmeDetalhes.filmeVotado = null;
                DiretorDetalhes.diretorVotado = null;
                GravarVoto.u = usuario;


                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
                finish();
            }
            else{
                ConfirmarActivity.confirmação = true;

                filme = filme.replaceAll("_"," ");
                FilmeDetalhes.filmeVotado = filme;

                diretor = diretor.replaceAll("_"," ");
                DiretorDetalhes.diretorVotado = diretor;

                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }
    }
}
