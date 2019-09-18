package com.example.dynamodb.cotacoes;

import java.text.MessageFormat;

public enum  UrlColetoresEnum {

    DOLAR("https://ptax.bcb.gov.br/ptax_internet/consultaBoletim.do?method=gerarCSVFechamentoMoedaNoPeriodo&ChkMoeda=61&DATAINI={0}&DATAFIM={1}"),
    SOJA("https://www.cepea.esalq.usp.br/br/consultas-ao-banco-de-dados-do-site.aspx?tabela_id=12&data_inicial={0}&periodicidade=1&data_final={1}"),
    MILHO("https://www.cepea.esalq.usp.br/br/consultas-ao-banco-de-dados-do-site.aspx?tabela_id=77&data_inicial={0}&periodicidade=1&data_final={1}"),
    DIESEL("https://ptax.bcb.gov.br/ptax_internet/consultaBoletim.do?method=gerarCSVFechamentoMoedaNoPeriodo&ChkMoeda=61&DATAINI={0}&DATAFIM={1}");

    private String urlTemplate;

    UrlColetoresEnum(String urlTemplate) {
        this.urlTemplate = urlTemplate;

    }

    public String getUrlTemplate(Object[] template) {
        return MessageFormat.format(urlTemplate, template);
    }
}
