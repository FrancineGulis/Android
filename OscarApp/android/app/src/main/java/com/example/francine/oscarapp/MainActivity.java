package com.example.francine.oscarapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == R.id.votarF){
            Intent i = new Intent(this, VotarFilmeActivity.class);
            startActivity(i);
            return true;
        }

        else if(id == R.id.votarD){
                Intent i = new Intent(this, VotarDiretorActivity.class);
                startActivity(i);
                return true;
        }
        else if(id == R.id.confirmar){
                Intent i = new Intent(this, ConfirmarActivity.class);
                startActivity(i);
                return true;
        }
        else if(id == R.id.sair){
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
            finish();
            return true;
        }else
            return super.onOptionsItemSelected(item);
    }
}
