package com.example.apiprojectdeputaddos.DTO;

import java.util.List;

public class DadosDeputado {


    //DTO - DATA TRANSFER OBJECT
    private List<Dados> dados;
    private List<Link> links;

    public List<Dados> getDados() {
        return dados;
    }

    public void setDados(List<Dados> dados) {
        this.dados = dados;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    @Override
    public String toString() {
        return "DadosDeputadoDTO{" +
                "dados=" + dados +
                ", links=" + links +
                '}';
    }
}
