package com.example.apiprojectdeputaddos.service;

import com.example.apiprojectdeputaddos.DTO.DadosDeputado;
import com.example.apiprojectdeputaddos.DTO.DespesasDTO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DeputadoService {


    @GET("deputados")
    Call<DadosDeputado> buscarDadosDeputado(@Query("nome") String nome);

    @GET("deputados/{id}/despesas")
    Call<DespesasDTO> buscarDespesasDeputado(@Path("id") Integer id);
}
