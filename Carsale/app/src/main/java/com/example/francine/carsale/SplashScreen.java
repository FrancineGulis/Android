package com.example.francine.carsale;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Francine on 26/10/2017.
 */

public class SplashScreen extends Activity{

    private static String url = "https://dl.dropboxusercontent.com/s/5196pefx0hh7r32/carros2.json";
    private Carro carro;

    ArrayList<Carro> carList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        carList = new ArrayList<>();

        new SplashScreen.GetCars().execute();
    }

    private class GetCars extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0){
            HttpHandler sh = new HttpHandler();

            //Faz request e recebe resposta
            String jsonStr = sh.makeServiceCall(url);

            if(jsonStr != null){
                try{
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    JSONArray carros = jsonObj.getJSONArray("carros");

                    //percorrendo o array e pegando os carros
                    for(int i = 0; i < carros.length(); i++){
                        JSONObject c = carros.getJSONObject(i);

                        String id = c.getString("id");
                        String foto = c.getString("foto");
                        String modelo = c.getString("modelo");
                        String fabricante = c.getString("fabricante");
                        String ano = c.getString("ano");
                        String cor = c.getString("cor");
                        String preco = c.getString("preco");

                        Bitmap imagemBitmap = null;

                        try {
                            imagemBitmap = HttpHandler.baixarImagem(foto);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        foto = encodeTobase64(imagemBitmap);

                        //temp objeto para um carro
                        carro = new Carro(id, foto, modelo, fabricante, ano, cor, preco);

                        //adicionando carro na lista
                        carList.add(carro);

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

            for(Carro c: carList){
                System.out.println("Carro: "+c.getModelo());
                System.out.println("Carro: "+c.getPreco());
            }

            Intent i = new Intent(SplashScreen.this, MainActivity.class);
            Bundle args = new Bundle();
            args.putSerializable("ARRAYLIST", (Serializable) carList);
            i.putExtra("carList",args);
            startActivity(i);
            finish();
        }
    }//Fim GetCars

    public static String encodeTobase64(Bitmap image) {
        Bitmap immagex = image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immagex.compress(Bitmap.CompressFormat.JPEG, 90, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);
        return imageEncoded;
    }

    public static Bitmap decodeBase64(String input) {
        byte[] decodedByte = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
    }
}//Fim SplashScreen
