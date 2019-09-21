package com.example.dynamodb.cotacoes.modelagem;

import com.example.dynamodb.cotacoes.modelagem.infra.CepeaHttp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CotacaoColetorFactory {


    static Map<String, CotacaoColetor> proxy = new HashMap<>();
    static {
        proxy.put("DIESEL", new CotacaoDiesel());
        proxy.put("SOJA_BOLSA", new CotacaoSojaBolsa(new CepeaHttp()));

    }

    public List<Object> execute(String tipo) {
        return factory(tipo).coletar("oi");
    }

    private CotacaoColetor factory(String tipo) {

        return proxy.get(tipo);
//        switch (tipo) {
//            case "DIESEL" :
//                return new CotacaoDiesel();
//            case "DOLAR":
//                return new CotacaoDolar();
//            case "MILHO_BOLSA":
//                return new CotacaoMilhoBolsa(new CepeaHttp());
//            case "MILHO_CEPEA":
//                return new CotacaoMilhoCepea(new CepeaHttp());
//            case "SOJA_BOLSA":
//                return new CotacaoSojaBolsa(new CepeaHttp());
//            case "SOJA_CEPEA":
//                return new CotacaoSojaCepea(new CepeaHttp());
//            default:
//                return null;
//        }
    }
}
