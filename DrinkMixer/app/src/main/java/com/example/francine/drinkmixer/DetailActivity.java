package com.example.francine.drinkmixer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        int drinkId = 3;//Valor default para erro
        Intent it = getIntent();
        String[] drinks = new String[]{"Batida de Sonho de Valsa", "Drink dos Deuses", "Quentão"};

        String[] ingredientes = new String[]{"3 bombons Sonho de Valsa\n" + "1/2 garrafa de pinga...\n",
                "1 vidro de leite de coco\n" + "1 copo de suco de maracujá...\n",
                "1 litro de cachaça\n" + "600 ml de água...\n"};

        String[] preparo = new String[]{"Bater tudo no liquidificador\n" + "Servir gelado\n",
            "Bater os 5 ingredientes no liquidificador\n",
            "Colocar em uma panela grande\n" + "Manter no  fogo após o preparo...\n"};

        EditText drinkName = (EditText) findViewById(R.id.drinkName);
        EditText ingBox = (EditText) findViewById(R.id.ingEditText);
        EditText prepBox = (EditText) findViewById(R.id.prepEditText);

        if(it!=null)
            drinkId = it.getIntExtra("drinkId",3);

        if(drinkId==3){
            drinkName.setText("");
            ingBox.setText("");
            prepBox.setText("");
            Toast.makeText(this,"Drink não encontrado", Toast.LENGTH_LONG).show();
        }
        else{
            drinkName.setText(drinks[drinkId]);
            ingBox.setText(ingredientes[drinkId]);
            prepBox.setText(preparo[drinkId]);
        }
    }
}
