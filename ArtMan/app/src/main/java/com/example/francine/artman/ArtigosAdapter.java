package com.example.francine.artman;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Francine on 01/11/2017.
 */

public abstract class ArtigosAdapter extends BaseAdapter {
    private List<Artigo> artigos;
    private LayoutInflater inflater;

    public ArtigosAdapter(Context context, List<Artigo> artigos){
        this.inflater = (LayoutInflater)context.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
        this.artigos = artigos;
    }

    public void novosDados(List<Artigo> artigos){
        this.artigos = artigos;
        notifyDataSetChanged();
    }

    @Override
    public int getCount(){
        return artigos.size();
    }

    @Override
    public Object getItem(int position){
        return artigos.get(position);
    }

    @Override
    //Retorna um id específico ppara cada elemento a lista
    public long getItemId(int arg0){
        return 0;
    }

    @Override
    public View getView(final int position, View arg1, ViewGroup arg2){
        View v = inflater.inflate(R.layout.item_artigo, null);
        ((TextView)(v.findViewById(R.id.txtNome))).setText(artigos.get(position).nome);
        ((ImageButton)
                (v.findViewById(R.id.btnEditar))).setOnClickListener(new  View.OnClickListener(){
                @Override
                public void onClick(View v){
                    edita(artigos.get(position));
                }
        });

        ((ImageButton)
                (v.findViewById(R.id.btnExcluir))).setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    deleta(artigos.get(position));
                }
        });

        return v;
    }

    public abstract void edita(Artigo artigo);
    public abstract void deleta(Artigo artigo);
}
