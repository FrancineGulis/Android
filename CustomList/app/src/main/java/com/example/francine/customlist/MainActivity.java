package com.example.francine.customlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ListView list;
    String[] refri = {"Coca", "Fanta"};
    Integer[] imageId = {R.drawable.coca, R.drawable.fanta};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListCell adapter = new ListCell(MainActivity.this, refri, imageId);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3){
                Toast.makeText(MainActivity.this, "Clicou na " +refri[+ arg2], Toast.LENGTH_SHORT).show();
            }
        });
    }
}
