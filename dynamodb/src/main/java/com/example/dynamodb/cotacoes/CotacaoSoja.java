package com.example.dynamodb.cotacoes;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CotacaoSoja extends CotacaoAbastract implements Cotacao {

//    https://www.cepea.esalq.usp.br/br/consultas-ao-banco-de-dados-do-site.aspx?tabela_id=dolar&data_inicial=17%2F09%2F2019&periodicidade=1&data_final=17%2F09%2F2019
//{
//    com link download xls
//}

    @Override
    public CotacaoRegistro executar() {
        final List<String> listaLinhas = baixarArquivo(UrlColetoresEnum.SOJA.getUrlTemplate(inicioFim()));

        return null;
    }


}
