package com.example.dynamodb.cotacoes;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;

@DynamoDBDocument
public class CotacaoValorModel {

    private String tipo;
    private Double valor;
    private Double variacao;
    private String uf;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Double getVariacao() {
        return variacao;
    }

    public void setVariacao(Double variacao) {
        this.variacao = variacao;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    @Override
    public String toString() {
        return "CotacaoValorModel{" +
                "tipo='" + tipo + '\'' +
                ", valor=" + valor +
                ", variacao=" + variacao +
                ", uf='" + uf + '\'' +
                '}';
    }
}
