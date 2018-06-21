package com.example.francine.openweathermap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements Response.Listener,
    Response.ErrorListener{

    public static final String REQUEST_TAG = "MainVolleyActivity";
    private TextView mTextView;
    private Button mButton;
    private RequestQueue mQueue;

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         mTextView = (TextView)findViewById(R.id.textView);
         mButton = (Button)findViewById(R.id.button);
    }

    @Override
    protected void onStart(){
        super.onStart();
        mQueue = CustomVolleyRequestQueue.getmInstance(this.getApplicationContext())
                .getRequestQueue();

        String url = "http://api.openweathermap.org/data/2.5/weather?q=atlanta,us&APPID=08740dcd84b84bb8971952aa16120cae";

        final CustomJSONObjectRequest jsonRequest = new CustomJSONObjectRequest(Request.Method
        .GET, url, new JSONObject(), this, this);

        jsonRequest.setTag(REQUEST_TAG);

        mButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mQueue.add(jsonRequest);
            }
        });
    }

    @Override
    protected void onStop(){
        super.onStop();
        if(mQueue != null){
            mQueue.cancelAll(REQUEST_TAG);
        }
    }

    @Override
    public void onErrorResponse(VolleyError error){
        mTextView.setText(error.getMessage());
    }

    @Override
    public void onResponse(Object response){
        mTextView.setText("Response is: " + response);
        try{
            mTextView.setText(mTextView.getText()
            + "\n\n" + ((JSONObject) response).getString("name")
            + "\n\n" + ((JSONObject) response).getJSONObject("main").toString()
            + "\n\n" + ((JSONObject) response).getJSONObject("main").getDouble("temp_min"));
        }catch (JSONException e){
            e.printStackTrace();
        }
    }
}
