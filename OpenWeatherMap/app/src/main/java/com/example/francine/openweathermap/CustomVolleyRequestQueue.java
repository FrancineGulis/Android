package com.example.francine.openweathermap;

import android.app.DownloadManager;
import android.content.Context;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;

/**
 * Created by Francine on 23/10/2017.
 */

public class CustomVolleyRequestQueue {
    private static CustomVolleyRequestQueue mInstance;
    private static Context mCtx;
    private RequestQueue mRequestQueue;

    private CustomVolleyRequestQueue(Context context){
        //Recebe o contexto da aplicação
        //Assim a fila dura o ciclo de vida do app
        mCtx = context;
        mRequestQueue = getRequestQueue();
    }

    public static synchronized CustomVolleyRequestQueue getmInstance(Context context){
        if(mInstance == null){
            mInstance = new CustomVolleyRequestQueue(context);
        }
        //Retorna uma instância da fila
        return mInstance;
    }

    public RequestQueue getRequestQueue(){
        if(mRequestQueue == null){
            //Cache de 10MB
            Cache cache = new DiskBasedCache(mCtx.getCacheDir(), 10*1024*1024);
            Network network = new BasicNetwork(new HurlStack());
            mRequestQueue = new RequestQueue(cache, network);
            mRequestQueue.start();
        }
        return mRequestQueue;
    }
}
