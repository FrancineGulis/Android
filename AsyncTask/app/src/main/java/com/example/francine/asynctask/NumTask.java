package com.example.francine.asynctask;

import android.os.AsyncTask;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Francine on 21/10/2017.
 */

public class NumTask extends AsyncTask<Integer, Integer, Void> {
    private TextView text;
    private Button bt;

    public NumTask(TextView text, Button  bt){
        this.text = text;
        this.bt = bt;
    }

    @Override
    protected Void doInBackground (Integer... params){
        int n = params[0];
        int i = 0;

        while (i < n){
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

            publishProgress(i);
            i++;
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values){
        super.onProgressUpdate(values);
        text.setText(String.valueOf(values[0]));
    }
}
