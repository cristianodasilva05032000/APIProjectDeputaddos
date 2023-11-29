package com.example.apiprojectdeputaddos;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class APIDeputados {

    private static final String BASE_URL = "https://dadosabertos.camara.leg.br/api/v2/";

    public void obterIdDoPrimeiroDeputado() {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                String endpoint = "deputados";
                String resposta = fazerRequisicaoGet(BASE_URL + endpoint);

                try {

                    JSONArray deputados = new JSONArray(resposta);
                    if (deputados.length() > 0) {

                        JSONObject primeiroDeputado = deputados.getJSONObject(0);
                        String id = primeiroDeputado.getString("id");

                        obterDetalhesDoDeputado(id);
                    }
                } catch (JSONException e) {
                    Log.e("DeputadosAPI", "Erro ao analisar a resposta JSON", e);
                }

                return null;
            }
        }.execute();
    }


    private void obterDetalhesDoDeputado(final String id) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                String endpoint = "deputados/" + id;
                String resposta = fazerRequisicaoGet(BASE_URL + endpoint);


                Log.d("DeputadosAPI", "Detalhes do deputado: " + resposta);

                return null;
            }
        }.execute();
    }

    private String fazerRequisicaoGet(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                bufferedReader.close();
                return stringBuilder.toString();
            } finally {
                urlConnection.disconnect();
            }
        } catch (IOException e) {
            Log.e("DeputadosAPI", "Erro ao fazer a requisição GET", e);
            return null;
        }
    }
}