package com.example.dynamodb.cotacoes.modelagem;

import java.util.ArrayList;
import java.util.List;

public class CotacaoSingleton {

    CotacaoColetorFactory factory = new CotacaoColetorFactory();

    public Object cotar(Object parametros) {

        //logar todo passo a passo

        List<Object> retorno = new ArrayList<>();

        List<String> tiposRetorno = new ArrayList<>();
        tiposRetorno.add("DIESEL");
        tiposRetorno.add("SOJA_BOLSA");

        //ontem e hoje
        final List<Object> objects = buscarDynamoOntemHoje();
        final List<Object> hoje;
        final List<Object> ontem;

        for (String s : tiposRetorno) {

            //if vazio buscar
            for (Object object : objects) {
                //filtrar tipo
                //se conter nos dados que estavem do dynamo adiciona no retorno
                //se nao conter busca na internet
                // retorno sucesso internet salva no array p ir po dinamo e add no
                // obj retorno
                // retorno erro internet
                // busca o de ontem
                // nao achou de ontem
                // retorna com valor 0

            }

        }

        return null;


    }

    public List<Object> buscarDynamoOntemHoje() {

        return new ArrayList<>();
    }
}
