package com.example.apiprojectdeputaddos.DTO;

import java.util.List;

public class DespesasDTO {



    private List<DadosDespesas> dados;
    private List<Link> links;

    public List<DadosDespesas> getDados() {
        return dados;
    }

    public void setDados(List<DadosDespesas> dados) {
        this.dados = dados;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

}