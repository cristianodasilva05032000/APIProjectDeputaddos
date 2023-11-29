package com.example.apiprojectdeputaddos;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apiprojectdeputaddos.DTO.Dados;
import com.example.apiprojectdeputaddos.DTO.DadosDeputado;
import com.example.apiprojectdeputaddos.DTO.DadosDespesas;
import com.example.apiprojectdeputaddos.DTO.DespesasDTO;
import com.example.apiprojectdeputaddos.adapter.DeputadosListAdapter;
import com.example.apiprojectdeputaddos.adapter.DespesasListAdapter;
import com.example.apiprojectdeputaddos.controller.Deputados;
import com.example.apiprojectdeputaddos.listener.AtualizaListaListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements AtualizaListaListener {

    private API executaAPI;
    private ListView lvDeputados;
    private TextView tvDeputados;
    private EditText edNome;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvDeputados = findViewById(R.id.lvDeputados);
        tvDeputados = findViewById(R.id.tvDeputados);
        edNome = findViewById(R.id.edNome);

        Dados dados = new Dados();

        DeputadosListAdapter adapter =
                new DeputadosListAdapter(this,
                        (ArrayList<Dados>) dados.getDados());

        lvDeputados.setAdapter(adapter);

        Deputados.buscarDadosDeputado("Socorro", tvDeputados);


        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Carregando");
        progressDialog.setTitle("Sincronizar Dados");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            progressDialog.dismiss();
                        }
                    });
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();


    }

    public void getDeputados(View view) {
        new API(this, this).execute(edNome.getText().toString());

    }



    @Override
    public void atualizaLista(DadosDeputado dados) {
        DeputadosListAdapter adapter =
                new DeputadosListAdapter(this,
                        (ArrayList<Dados>) dados.getDados());

        lvDeputados.setAdapter(adapter);


    }

    @Override
    public void atualizaDespesas(DespesasDTO dados) {
        DespesasListAdapter adapter =
                new DespesasListAdapter(this, (ArrayList<DadosDespesas>) dados.getDados());


    }

}