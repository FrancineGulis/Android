package com.example.francine.carsale;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Francine on 29/10/2017.
 */

public class CarroAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Carro> list;
    private Carro c;
    private View layout;

    public CarroAdapter(Context context, ArrayList<Carro> list) {
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
        c = list.get(position);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            layout = inflater.inflate(R.layout.list_item, null, true);
        } else {
            layout = convertView;
        }

        ImageView img = (ImageView) layout.findViewById(R.id.bitmap);
        Bitmap bitmap = SplashScreen.decodeBase64(c.getImagem());
        img.setImageBitmap(bitmap);

        TextView modelo = (TextView) layout.findViewById(R.id.modelo);
        modelo.setText(c.getModelo());

        TextView preco = (TextView) layout.findViewById(R.id.preco);
        preco.setText(c.getPreco());

        return layout;
    }
}