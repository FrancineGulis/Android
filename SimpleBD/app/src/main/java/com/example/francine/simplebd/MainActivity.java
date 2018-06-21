package com.example.francine.simplebd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private StudentOperations studentDBoperation;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        studentDBoperation = new StudentOperations(this);
        studentDBoperation.open();

        List values = studentDBoperation.getAllStudents();

        list = (ListView)findViewById(R.id.list);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, values);

        list.setAdapter(adapter);
    }

    public void addUser(View view){
        ArrayAdapter adapter = (ArrayAdapter) list.getAdapter();

        EditText text = (EditText)findViewById(R.id.input);
        Student stud = studentDBoperation.addStudent(text.getText().toString());

        adapter.add(stud);
    }

    public void deleteFirstUser(View view) {
        ArrayAdapter adapter = (ArrayAdapter) list.getAdapter();
        Student stud = null;

        if (list.getAdapter().getCount() > 0){
            stud = (Student) list.getAdapter().getItem(0);
            studentDBoperation.deleteStudent(stud);
            adapter.remove(stud);
        }
    }

    @Override
    protected void onResume(){
        studentDBoperation.open();
        super.onResume();
    }

    @Override
    protected void onPause(){
        studentDBoperation.close();
        super.onPause();
    }
}
