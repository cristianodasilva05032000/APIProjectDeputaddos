package com.example.apiprojectdeputaddos;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.apiprojectdeputaddos.DTO.DadosDeputado;
import com.example.apiprojectdeputaddos.DTO.DespesasDTO;
import com.example.apiprojectdeputaddos.listener.AtualizaListaListener;
import com.example.apiprojectdeputaddos.retrofit.RetrofitConfig;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class API extends AsyncTask<String, Void, Void> {

    private final Context ctx;
    private ProgressDialog progressBar;
    private final AtualizaListaListener listener;

    public API(Context ctx, AtualizaListaListener listener) {
        this.ctx = ctx;
        this.listener = listener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressBar = ProgressDialog.show(ctx, null, "Aguarde");
    }

    @Override
    protected Void doInBackground(String... strings) {
        String nome = strings[0];
        executarAPI(nome);
        return null;
    }

    private void executarAPI(String nome) {
        try {
            RetrofitConfig retrofitConfig = new RetrofitConfig();

            Call<DadosDeputado> call = retrofitConfig.deputadoService().buscarDadosDeputado(nome);
            call.enqueue(new Callback<DadosDeputado>() {
                @Override
                public void onResponse(Call<DadosDeputado> call, Response<DadosDeputado> response) {
                    DadosDeputado dadosDeputado = response.body();
                    if (dadosDeputado != null && !dadosDeputado.getDados().isEmpty()) {
                        executarAPIDespesas(dadosDeputado.getDados().get(0).getId());
                        listener.atualizaLista(dadosDeputado);
                        progressBar.dismiss();
                        Toast.makeText(ctx, "Execução finalizada!", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<DadosDeputado> call, Throwable t) {
                    Log.e("PHS", "ERRO AO CHAMAR API: " + t.getMessage());
                }
            });
        } catch (Exception ex) {
            Log.e("PHS", "Erro: " + ex.getMessage());
        }
    }

    private void executarAPIDespesas(int id) {
        try {
            RetrofitConfig retrofitConfig = new RetrofitConfig();

            Call<DespesasDTO> callDespesas = retrofitConfig.deputadoService().buscarDespesasDeputado(id);

            callDespesas.enqueue(new Callback<DespesasDTO>() {
                @Override
                public void onResponse(Call<DespesasDTO> callDespesas, Response<DespesasDTO> response) {
                    DespesasDTO despesasDTO = response.body();
                    listener.atualizaDespesas(despesasDTO);
                    progressBar.dismiss();
                    Toast.makeText(ctx, "Execução finalizada! " + (despesasDTO != null ? despesasDTO.getDados().size() : 0) + " Despesa(s) encontrada(s)!",
                            Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(Call<DespesasDTO> callDespesas, Throwable t) {
                    Log.e("PHS", "ERRO AO CHAMAR API: " + t.getMessage());
                }
            });
        } catch (Exception ex) {
            Log.e("PHS", "Erro: " + ex.getMessage());
        }
    }
}
