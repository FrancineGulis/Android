package com.example.francine.oscarapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class VotarDiretorActivity extends AppCompatActivity {
    private ProgressDialog pDialog;
    private ListView lv;
    private Diretor diretor;
    private DiretorAdapter adapter;
    private TextView txt;
    private static String url = "https://dl.dropbox.com/s/4scnaqzioi3ivxc/diretor.json";


    ArrayList<Diretor> diretorList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_votar_diretor);

        diretorList = new ArrayList<>();
        lv = (ListView)findViewById(R.id.list);

        if(ConfirmarActivity.confirmação==false)
            new VotarDiretorActivity.GetDiretores().execute();
        else{
            txt = (TextView)findViewById(R.id.textView);
            txt.setText("Votação encerrada!");
        }
    }

    private class GetDiretores extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            //Showing progress dialog
            pDialog = new ProgressDialog(VotarDiretorActivity.this);
            pDialog.setMessage("Aguarde... \nCarregando Diretores");
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

                    JSONArray diretores = jsonObj.getJSONArray("diretor");

                    //percorrendo o array e pegando diretores
                    for(int i = 0; i < diretores.length(); i++){
                        JSONObject d = diretores.getJSONObject(i);

                        String id = d.getString("id");
                        String nome = d.getString("nome");

                        diretor = new Diretor(id, nome);

                        //adicionando diretor na lista
                        diretorList.add(diretor);
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
            adapter = new DiretorAdapter(VotarDiretorActivity.this, diretorList);
            lv.setAdapter(adapter);

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> listView, View view, int position, long id){
                    Intent i = new Intent(listView.getContext(),DiretorDetalhes.class);

                    Bundle args = new Bundle();
                    args.putSerializable("ARRAYLIST", diretorList);
                    args.putInt("diretorId", position);
                    i.putExtra("args", args);
                    startActivity(i);
                }
            });
        }
    }//Fim GetDiretores
}
