package com.example.francine.webvolley;

import android.app.VoiceInteractor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements Response.Listener,
        Response.ErrorListener{

    public static final String REQUEST_TAG = "UserAutentication";
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
        String url = "http://192.168.100.6:8084/UserAutenticatorz/UserValidator?login=prof";
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
        if (mQueue != null){
            mQueue.cancelAll(REQUEST_TAG);
        }
    }

    @Override
    public void onErrorResponse(VolleyError error){
        mTextView.setText(error.getMessage());
    }

    @Override
    public void onResponse(Object response){
        mTextView.setText("Resposta:" + response);
        try {
            mTextView.setText(mTextView.getText()
                    + "\n\n" + ((JSONObject)response).getString("message"));
        }catch(JSONException e){
            e.printStackTrace();
        }
    }
}
