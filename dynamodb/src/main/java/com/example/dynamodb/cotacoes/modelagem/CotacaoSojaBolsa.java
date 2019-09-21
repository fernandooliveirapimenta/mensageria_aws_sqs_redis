package com.example.dynamodb.cotacoes.modelagem;

import com.example.dynamodb.cotacoes.modelagem.infra.CepeaHttp;

import java.util.List;

public class CotacaoSojaBolsa implements CotacaoColetor {
    private CepeaHttp http;

    public CotacaoSojaBolsa(CepeaHttp http){

        this.http = http;
    }
    @Override
    public List<Object> coletar(Object p) {
        return http.coletar(p);
    }
}
