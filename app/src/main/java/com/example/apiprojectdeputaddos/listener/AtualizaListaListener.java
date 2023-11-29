package com.example.apiprojectdeputaddos.listener;

import android.view.View;

import com.example.apiprojectdeputaddos.DTO.DadosDeputado;
import com.example.apiprojectdeputaddos.DTO.DespesasDTO;

public interface AtualizaListaListener {

    void atualizaLista(DadosDeputado dados);

    void getDeputados(View view);

    void atualizaDespesas(DespesasDTO dados);

}
