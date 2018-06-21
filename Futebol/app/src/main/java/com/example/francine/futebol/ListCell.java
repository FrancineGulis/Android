package com.example.francine.futebol;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Francine on 23/09/2017.
 */

public class ListCell extends ArrayAdapter<String>{
    private final Activity context;
    private final String[] time;
    private final String[] cidade;
    private final String[] estado;
    private final Integer[] imageId;

    public ListCell(Activity context, Integer[] imageId, String[] time, String[] cidade, String[] estado){
        super(context, R.layout.list_cell, time);
        this.context = context;
        this.time = time;
        this.cidade = cidade;
        this.estado = estado;
        this.imageId = imageId;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list_cell, null, true);

        TextView txtTitle = (TextView)rowView.findViewById(R.id.txt);
        TextView txtCid = (TextView)rowView.findViewById(R.id.textView2);
        TextView txtEst = (TextView)rowView.findViewById(R.id.textView3);
        ImageView imageView = (ImageView)rowView.findViewById(R.id.img);

        txtTitle.setText(time[position]);
        txtCid.setText(cidade[position]);
        txtEst.setText(estado[position]);
        imageView.setImageResource(imageId[position]);
        return rowView;
    }

}
