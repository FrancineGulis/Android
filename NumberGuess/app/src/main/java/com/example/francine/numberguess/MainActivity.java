package com.example.francine.numberguess;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int num, tries;
    Random r;
    TextView out;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        out = (TextView)findViewById(R.id.hint);
        out.setText("");

        r = new Random();
        num = r.nextInt(100)+1;
        System.out.println(num);
        tries = 0;
    }

    public void guess(View view){
        EditText chute = (EditText)findViewById(R.id.input);

        if(chute.length()!=0){
            int n = Integer.parseInt(chute.getText().toString());
            tries++;

            if(n == num)
                out.setText(String.valueOf("Acertou, você usou "+tries+" tentativas"));

            else if(n > num)
                out.setText(String.valueOf("O numero sorteado é menor"));

            else
                out.setText(String.valueOf("O numero sorteado é maior"));
        }

        else{
            Toast msg = Toast.makeText(this, "Digite um número", Toast.LENGTH_SHORT);
            msg.show();
        }
    }
}
