package com.example.apiprojectdeputaddos.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.apiprojectdeputaddos.DTO.Dados;
import com.example.apiprojectdeputaddos.R;

import java.util.ArrayList;

public class DeputadosListAdapter extends BaseAdapter {

    private final Context context;
    private final ArrayList<Dados>lista;

    public DeputadosListAdapter(Context context,
                                ArrayList<Dados> lista) {

        this.context = context;
        this.lista = lista;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int i) {
        return lista.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null){
            view = LayoutInflater.from(context).
                    inflate(R.layout.deputados_list_item,
                            viewGroup, false);
        }
        TextView tvNome = view.findViewById(R.id.tvNome);
        Dados dado = lista.get(i);
        tvNome.setText(dado.getNome()+
                " - "+dado.getSiglaPartido()+
                "/"+dado.getSiglaUf());

        return view;
    }
}
