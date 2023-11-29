package com.example.apiprojectdeputaddos.controller;

import android.util.Log;
import android.widget.TextView;

import com.example.apiprojectdeputaddos.DTO.DadosDeputado;
import com.example.apiprojectdeputaddos.retrofit.RetrofitConfig;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Deputados {

    public static void buscarDadosDeputado(String nome, TextView tvNome){
        try{
            Call<DadosDeputado> call = new RetrofitConfig()
                    .deputadoService().buscarDadosDeputado(nome);

            call.enqueue(new Callback<DadosDeputado>() {
                @Override
                public void onResponse(Call<DadosDeputado> call,
                                       Response<DadosDeputado> response) {
                    DadosDeputado dto = response.body();
                    tvNome.setText(dto.toString());
                }

                @Override
                public void onFailure(Call<DadosDeputado> call, Throwable t) {
                    Log.e("TESTE",
                            "Erro ao chamar API: "+t.getMessage());
                }
            });


        }catch (Exception ex){
            String erro = ex.getMessage();
        }
    }
}