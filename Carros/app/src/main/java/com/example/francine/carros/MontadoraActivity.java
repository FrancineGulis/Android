package com.example.francine.carros;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class MontadoraActivity extends ListActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String[] montadora = new String[]{"Fiat", "Chevrolet", "Volkswagem", "Honda"};

        ArrayAdapter<String> array = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, montadora);

        setListAdapter(array);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id){
        super.onListItemClick(l,v,position,id);

        Intent it=null;

        if(position==0)
            it = new Intent(this, Fiat.class);

        if(position==1)
            it = new Intent(this, Chevrolet.class);

        if(position==2)
            it = new Intent(this, Volkswagem.class);

        if(position==3)
            it = new Intent(this, Honda.class);

        startActivity(it);
    }
}
