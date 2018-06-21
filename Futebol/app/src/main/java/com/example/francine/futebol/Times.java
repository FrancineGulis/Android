package com.example.francine.futebol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class Times extends AppCompatActivity {
    ListView list;
    String[] time = {"Atlético", "Coritiba", "Paraná"};
    String[] cidade = {"Curitiba", "Curitiba", "Curitiba"};
    String[] estado = {"Paraná", "Paraná", "Paraná"};
    Integer[] imageId = {R.drawable.atletico, R.drawable.coritiba, R.drawable.parana};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_times);

        ListCell adapter = new ListCell(Times.this, imageId, time, cidade, estado);
        list = (ListView)findViewById(R.id.list);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> listView, View view, int position, long id){
                Intent intent = new Intent(listView.getContext(),Detalhes.class);

                intent.putExtra("timeId",position);
                startActivity(intent);
            }
        });
    }

    public void sair(View view){
        finish();
    }
}
