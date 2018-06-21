package com.example.francine.jsoncar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView carInfo = (TextView)findViewById(R.id.carInfo);

        try{
            carInfo.setText(adicaoSimplesDeDados().toString());
            carInfo.setText(carInfo.getText() + "\n" + adicaoDeUmObjeto());
        }catch (JSONException e){
            //TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private JSONObject adicaoSimplesDeDados() throws JSONException{
        //Criação do objeto carro e atribuição dos valores
        Carro carro = new Carro();
        carro.setId(1);
        carro.setModelo("Celta");
        carro.setPlaca("AAA1234");

        //Criação do objeto carroJson
        JSONObject carroJson = new JSONObject();
        //Inserção dos valores do carro no objeto JSON
        carroJson.put("id", carro.getId());
        carroJson.put("Modelo", carro.getModelo());
        carroJson.put("Placa", carro.getPlaca());

        //retorno do objeto JSON
        return carroJson;
    }

    private String adicaoDeUmObjeto() throws JSONException{
        Carro carro = new Carro();
        carro.setId(2);
        carro.setModelo("Celta");
        carro.setPlaca("AAA1234");

        JSONObject carroJson = new JSONObject();
        //Adição do objeto carro
        carroJson.put("Carro", carro);

        return carroJson.toString();
    }
}
