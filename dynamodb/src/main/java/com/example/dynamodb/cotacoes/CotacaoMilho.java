package com.example.dynamodb.cotacoes;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.ss.usermodel.*;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.*;

@Component
public class CotacaoMilho extends CotacaoAbastract implements Cotacao {

//    https://www.cepea.esalq.usp.br/br/consultas-ao-banco-de-dados-do-site.aspx?tabela_id=dolar&data_inicial=17%2F09%2F2019&periodicidade=1&data_final=17%2F09%2F2019
//{
//    com link download lerXlsCepea
//}



    @Override
    public CotacaoRegistro executar() {

        final String urlMilhoBovespa = "https://www.cepea.esalq.usp.br/br/indicador/series/milho.aspx?id=77";
        final String urlMilhoCepea = "https://www.cepea.esalq.usp.br/br/indicador/series/milho.aspx?id=17";

         restTemplate.execute(urlMilhoBovespa, HttpMethod.GET, null, clientHttpResponse -> {
            final InputStream arquivo = clientHttpResponse.getBody();
             File ret = File.createTempFile("download", ".xsl");
             StreamUtils.copy(arquivo, new FileOutputStream(ret));
             try {
                 lerXlsCepea(ret, "milhobovespa");
             } catch (Exception e) {
                 throw new RuntimeException(e);
             }
             final boolean delete = ret.delete();
             System.out.println(delete);
             return arquivo;
        });

//        final InputStream inputStream = baixarArquivoStream(urlMilhoBovespa);
//        lerXlsCepea(inputStream);

//        final InputStream inputStreamCepea = baixarArquivoStream(urlMilhoBovespa);
//        lerXlsCepea(inputStreamCepea);
        return null;
    }

    public Map<String, Object> lerXlsCepea(File arquivo, String tipo) throws Exception {

            Workbook wb = WorkbookFactory.create(arquivo);
            Sheet datatypeSheet = wb.getSheetAt(0);

            final Row ultima = datatypeSheet.getRow(datatypeSheet.getLastRowNum());
            final Row penultima = datatypeSheet.getRow(datatypeSheet.getLastRowNum() - 1);
            final Map<String, Object> ultimaLinha = linha(ultima);
            final Map<String, Object> penultimaLinha = linha(penultima);

            final Double valorUltima = (Double)ultimaLinha.get("valorReal");
            final Double valorPenultima = (Double)penultimaLinha.get("valorReal");
            ultimaLinha.put("variacao", ((valorUltima - valorPenultima)/valorPenultima)*100.0);
            ultimaLinha.put("fonte", "CEPEA");
            ultimaLinha.put("dateTime", new Date());
            ultimaLinha.put("tipo", tipo);

            return ultimaLinha;
    }

    private Map<String, Object> linha(Row ultima) {
        String data = ultima.getCell(0).getStringCellValue();
        Double valorReal = ultima.getCell(1).getNumericCellValue();
        Double valorDolar = ultima.getCell(2).getNumericCellValue();

        Map<String, Object> obj = new HashMap<>();
        obj.put("data", data);
        obj.put("valorReal", valorReal);
        obj.put("valorDolar", valorDolar);
        return obj;

    }

    public CotacaoRegistro executar2() {

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
