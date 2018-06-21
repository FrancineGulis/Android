package com.example.francine.downloadtask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void eventHandler(View view){
        ImageView image = (ImageView)findViewById(R.id.imageView);
        EditText url = (EditText)findViewById(R.id.editText);

        DownloadTask runner = new DownloadTask(this, image);
        runner.execute(url.getText().toString());
    }
}
