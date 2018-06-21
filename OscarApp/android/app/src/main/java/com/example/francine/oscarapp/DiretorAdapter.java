package com.example.francine.oscarapp;

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
 * Created by Francine on 15/11/2017.
 */

public class DiretorAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Diretor> list;
    private Diretor d;
    private View layout;

    public DiretorAdapter(Context context, ArrayList<Diretor> list) {
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
        d = list.get(position);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            layout = inflater.inflate(R.layout.list_diretor, null, true);
        } else {
            layout = convertView;
        }

        TextView nome = (TextView) layout.findViewById(R.id.nome);
        nome.setText(d.getNome());

        return layout;
    }
}
