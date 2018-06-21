package com.example.francine.oscarapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

/**
 * Created by Francine on 14/11/2017.
 */

public class FilmeAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Filme> list;
    private Filme f;
    private View layout;

    public FilmeAdapter(Context context, ArrayList<Filme> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        f = list.get(position);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            layout = inflater.inflate(R.layout.list_filme, null, true);
        } else {
            layout = convertView;
        }

        ImageView img = (ImageView) layout.findViewById(R.id.imageView);
        Bitmap bitmap = decodeBase64(f.getImagem());
        img.setImageBitmap(bitmap);

        TextView nome = (TextView) layout.findViewById(R.id.nome);
        nome.setText(f.getNome());

        TextView genero = (TextView) layout.findViewById(R.id.genero);
        genero.setText(f.getGenero());

        return layout;
    }

    public static String encodeTobase64(Bitmap image) {
        Bitmap immagex = image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immagex.compress(Bitmap.CompressFormat.JPEG, 80, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);
        return imageEncoded;
    }

    public static Bitmap decodeBase64(String input) {
        byte[] decodedByte = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
    }
}
