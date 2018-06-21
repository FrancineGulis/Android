package com.example.francine.carsale;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ProgressDialog pDialog;
    private ListView lv;
    private static String url = "https://dl.dropboxusercontent.com/s/d24im9i7e3tczls/carros.json";
    public double valorVendido = 0;
    private CarroAdapter adapter;
    public static final int CAR_REQUEST = 1;

    ArrayList<Carro> carList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent it = getIntent();
        if(it != null){
            Bundle args = it.getBundleExtra("carList");
            carList = (ArrayList<Carro>)args.getSerializable("ARRAYLIST");
        }

        lv = (ListView)findViewById(R.id.list);
        adapter = new CarroAdapter(this, carList);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> listView, View view, int position, long id){
                Intent i = new Intent(listView.getContext(),Detalhes.class);

                Bundle args = new Bundle();
                args.putSerializable("ARRAYLIST", (Serializable) carList);
                args.putInt("carroId", position);
                i.putExtra("args",args);
                startActivityForResult(i, CAR_REQUEST);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent it) {
        // Check which request we're responding to
        if (requestCode == CAR_REQUEST) {
            // Make sure the request was successful
            if (resultCode == 1) {
                Bundle vendido = it.getBundleExtra("vendido");
                int carroId = vendido.getInt("carroId");
                String a = vendido.getString("preco");

                a = a.replaceAll("\\.","");
                a = a.replaceAll(",",".");

                valorVendido = valorVendido + Double.parseDouble(a);

                System.out.println("Valor vendido: "+ valorVendido);

                carList.remove(carroId);
                adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == R.id.menu_add){
            Intent intent = new Intent(this, RelatorioActivity.class);

            Bundle args = new Bundle();
            args.putDouble("vendido", valorVendido);
            intent.putExtra("vendido", args);
            startActivity(intent);
            return true;
        }else
            return super.onOptionsItemSelected(item);
    }
}

