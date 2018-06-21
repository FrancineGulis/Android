package com.example.francine.asynctasksleep;

import android.os.AsyncTask;
import android.widget.TextView;

/**
 * Created by Francine on 21/10/2017.
 */

public class SleepTask extends AsyncTask<String, String, String> {
    private String resp;
    private TextView finalResult;

    public SleepTask(TextView finalResult){
        this.finalResult = finalResult;
    }

    @Override
    protected String doInBackground(String... params){
        publishProgress("Dormindo...");

        try {
            int time = Integer.parseInt(params[0]);
            Thread.sleep(time);
            resp = "Dormiu por " + time/1000 + "segundos.";
        }catch (Exception e){
            e.printStackTrace();
        }

        return resp;
    }

    @Override
    protected void onProgressUpdate(String... values){
        super.onProgressUpdate(values);
        finalResult.setText(values[0]);
    }

    @Override
    protected void onPostExecute(String s){
        super.onPostExecute(s);
        finalResult.setText(s);
    }

}
