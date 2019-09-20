package com.example.dynamodb.cotacoes;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@DynamoDBTable(tableName = "cotacoes")
public class CotacoesModel {

    @DynamoDBHashKey
    private String dataCotacao;

    private List<CotacaoValorModel> valores;

    public String getDataCotacao() {
        return dataCotacao;
    }

    public void setDataCotacao(String dataCotacao) {
        this.dataCotacao = dataCotacao;
    }

    public List<CotacaoValorModel> getValores() {
        return valores;
    }

    public void setValores(List<CotacaoValorModel> valores) {
        this.valores = valores;
    }

    public List<CotacaoValorModel> filtrarPorTipo(String tipo){

        if(valores != null) {
            return valores.stream().filter(v -> v.getTipo().equalsIgnoreCase(tipo))
                    .collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public String toString() {
        return "CotacoesModel{" +
                "dataCotacao='" + dataCotacao + '\'' +
                ", valores=" + valores +
                '}';
    }
}
