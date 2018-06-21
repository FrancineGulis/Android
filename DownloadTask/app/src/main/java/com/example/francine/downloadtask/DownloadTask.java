package com.example.francine.downloadtask;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;

/**
 * Created by Francine on 23/10/2017.
 */

public class DownloadTask extends AsyncTask<String, Void, Bitmap> {
    private Context ctx;
    private ImageView image;
    private ProgressDialog progressDialog;

    public DownloadTask(Context ctx, ImageView image){
        this.ctx = ctx;
        this.image = image;
    }

    @Override
    protected void onPreExecute(){
        super.onPreExecute();
        progressDialog = ProgressDialog.show(ctx, "Por favor aguarde...", "Baixando imagem...");
    }

    @Override
    protected Bitmap doInBackground(String... params){
        Bitmap imagemBitmap = null;
        try{
            imagemBitmap = Auxiliar.baixarImagem("https://dl.dropboxusercontent.com/s/etyqre2lxgmgtzj/city.jpg");
        }catch (IOException e){
            e.printStackTrace();
        }
        return imagemBitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap){
        image.setImageBitmap(bitmap);
        progressDialog.dismiss();
    }

}
