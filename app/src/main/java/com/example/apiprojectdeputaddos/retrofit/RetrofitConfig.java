package com.example.apiprojectdeputaddos.retrofit;

import com.example.apiprojectdeputaddos.service.DeputadoService;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitConfig {

    private final Retrofit retrofit;
    private static final String BASE_URL = "https://dadosabertos.camara.leg.br/api/v2/";
    OkHttpClient okHttpClient = UnsafeOkHttpClient.getUnsafeOkHttpClient();

    public RetrofitConfig() {
        this.retrofit = new Retrofit.Builder()

                .baseUrl(BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

    public DeputadoService deputadoService(){
        return this.retrofit.create(DeputadoService.class);
    }

    public DeputadoService buscarDespesasDeputado(){
        return this.retrofit.create(DeputadoService.class);
    }


}
