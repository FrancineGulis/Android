package com.example.francine.json;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private ProgressDialog pDialog;
    private ListView lv;
    private static String url = "https://dl.dropbox.com/s/e85uplwzbsvscq3/times.json";

    ArrayList<HashMap<String, String>> teamList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        teamList = new ArrayList<>();
        lv = (ListView)findViewById(R.id.list);

        new GetTeams().execute();
    }

    private class GetTeams extends AsyncTask<Void, Void, Void>{

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            //Showing progress dialog
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Aguarde...");
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

                    JSONArray teams = jsonObj.getJSONArray("times");

                    //percorrendo o array e pegando times
                    for(int i = 0; i < teams.length(); i++){
                        JSONObject c = teams.getJSONObject(i);

                        String id = c.getString("id");
                        String nome = c.getString("nome");
                        String city = c.getString("cidade");
                        String series = c.getString("serie");

                        //temp hash map para um time
                        HashMap<String, String> team = new HashMap<>();

                        team.put("id",id);
                        team.put("nome",nome);
                        team.put("city",city);
                        team.put("series",series);

                        //adicionando time na lista
                        teamList.add(team);
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
            ListAdapter adapter = new SimpleAdapter(
                    MainActivity.this, teamList,
                    R.layout.list_item,
                    new String[]{"nome", "city", "series"},
                    new int[]{R.id.nome, R.id.city, R.id.series});

            lv.setAdapter(adapter);
        }
    }//Fim GetTeams
}//Fim MainActivity
