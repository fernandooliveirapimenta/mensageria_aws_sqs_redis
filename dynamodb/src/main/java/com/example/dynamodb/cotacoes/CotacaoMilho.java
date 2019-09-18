package com.example.dynamodb.cotacoes;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class CotacaoMilho extends CotacaoAbastract implements Cotacao {

//    https://www.cepea.esalq.usp.br/br/consultas-ao-banco-de-dados-do-site.aspx?tabela_id=dolar&data_inicial=17%2F09%2F2019&periodicidade=1&data_final=17%2F09%2F2019
//{
//    com link download xls
//}



    @Override
    public CotacaoRegistro executar() {

        final List<String> listaLinhas = baixarArquivo(UrlColetoresEnum.MILHO.getUrlTemplate(inicioFim()));
        ObjectMapper mapper = new ObjectMapper();

        final String json = listaLinhas.stream().findFirst().orElse("");
        try {
            if(!json.isEmpty()) {

                final JsonNode jsonNode = mapper.readTree(json);
                final JsonNode arquivo = jsonNode.path("arquivo");
                if(arquivo != null) {

                    final List<String> linhas = baixarArquivo(arquivo.asText());

                    if(linhas.size() >= 3){

                        CotacaoRegistro cotacaoRegistro = new CotacaoRegistro();
                        cotacaoRegistro.data = LocalDate.now();
                        cotacaoRegistro.valor = Double.valueOf(linhas.get(1).replace(",", "."));

                        return cotacaoRegistro;
                    }


                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return null;

    }

}
