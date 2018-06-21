package com.example.francine.oscarapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class VotarFilmeActivity extends AppCompatActivity {
    private ProgressDialog pDialog;
    private ListView lv;
    private Filme filme;
    private FilmeAdapter adapter;
    private TextView txt;
    private static String url = "https://dl.dropbox.com/s/luags6sv8uxdoj1/filme.json";

    ArrayList<Filme> filmeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_votar_filme);

        filmeList = new ArrayList<>();
        lv = (ListView)findViewById(R.id.list);

        if(ConfirmarActivity.confirmação==false)
            new GetFilmes().execute();
        else{
            txt = (TextView)findViewById(R.id.textView);
            txt.setText("Votação encerrada!");
        }

    }

    private class GetFilmes extends AsyncTask<Void, Void, Void>{

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            //Showing progress dialog
            pDialog = new ProgressDialog(VotarFilmeActivity.this);
            pDialog.setMessage("Aguarde... \nCarregando Filmes");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0){
            HttpHandler sh = new HttpHandler();

            //Faz request e recebe resposta
            String jsonStr = sh.makeServiceCall(url);

            if(jsonStr != null){
                try{
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    JSONArray filmes = jsonObj.getJSONArray("filme");

                    //percorrendo o array e pegando filmes
                    for(int i = 0; i < filmes.length(); i++){
                        JSONObject f = filmes.getJSONObject(i);

                        String id = f.getString("id");
                        String nome = f.getString("nome");
                        String genero = f.getString("genero");
                        String foto = f.getString("foto");

                        Bitmap imagemBitmap = null;

                        try {
                            imagemBitmap = HttpHandler.baixarImagem(foto);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        foto = FilmeAdapter.encodeTobase64(imagemBitmap);

                        filme = new Filme(id, nome, genero, foto);

                        //adicionando filme na lista
                        filmeList.add(filme);
                    }
                }catch (final JSONException e){
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });
                }
            }

            else{
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });
            }
            return null;
        }//fim doInBackground

        @Override
        protected void onPostExecute(Void result){
            super.onPostExecute(result);
            // Dispensar progress dialog
            if(pDialog.isShowing())
                pDialog.dismiss();

            //Mostrar dados da lista
            lv = (ListView)findViewById(R.id.list);
            adapter = new FilmeAdapter(VotarFilmeActivity.this, filmeList);
            lv.setAdapter(adapter);

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> listView, View view, int position, long id){
                    Intent i = new Intent(listView.getContext(),FilmeDetalhes.class);

                    Bundle args = new Bundle();
                    args.putSerializable("ARRAYLIST", filmeList);
                    args.putInt("filmeId", position);
                    i.putExtra("args", args);
                    startActivity(i);
                }
            });
        }
    }//Fim GetFilmes
}
