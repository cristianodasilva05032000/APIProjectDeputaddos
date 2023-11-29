package com.example.apiprojectdeputaddos.controller;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import com.example.apiprojectdeputaddos.DTO.DespesasDTO;
import com.example.apiprojectdeputaddos.retrofit.RetrofitConfig;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Despesas {

    public static void API(Integer id, Context ctx, TextView tvDespesas) {
        try {
            RetrofitConfig retrofitConfig = new RetrofitConfig();
            Call<DespesasDTO> call = retrofitConfig.deputadoService().buscarDespesasDeputado(id);

            call.enqueue(new Callback<DespesasDTO>() {
                @Override
                public void onResponse(Call<DespesasDTO> call, Response<DespesasDTO> response) {
                    if (response.isSuccessful()) {
                        DespesasDTO dto = response.body();
                        if (dto != null) {
                            tvDespesas.setText(dto.toString());
                        } else {
                            tvDespesas.setText("Sem Resultados");
                        }
                    } else {
                        tvDespesas.setText("Erro na chamada da API");
                    }
                }

                @Override
                public void onFailure(Call<DespesasDTO> call, Throwable t) {
                    Log.e("TESTE", "Erro ao chamar API: " + t.getMessage());
                    tvDespesas.setText("Erro ao chamar API");
                }
            });

        } catch (Exception ex) {
            Log.e("TESTE", "Erro: " + ex.getMessage());
            tvDespesas.setText("Erro: " + ex.getMessage());
        }
    }
}
