package com.example.dynamodb.cotacoes.modelagem;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
@Scope(value = WebApplicationContext.SCOPE_APPLICATION, proxyMode = ScopedProxyMode.TARGET_CLASS )
public class CotacaoSingleton {

    CotacaoColetorFactory factory = new CotacaoColetorFactory();
    private AtomicBoolean executando = new AtomicBoolean();

    public CotacaoSingleton() {
        executando.set(false); ;
    }

    public Object cotar(Object parametros) {

        //logar todo passo a passo

        List<Object> retorno = new ArrayList<>();

        List<String> tiposRetorno = new ArrayList<>();
        tiposRetorno.add("DIESEL");
        tiposRetorno.add("SOJA_BOLSA");

        final boolean executando = this.executando.get();

        if(!executando) {

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
        }


        return null;


    }

    public List<Object> buscarDynamoOntemHoje() {

        return new ArrayList<>();
    }
}
