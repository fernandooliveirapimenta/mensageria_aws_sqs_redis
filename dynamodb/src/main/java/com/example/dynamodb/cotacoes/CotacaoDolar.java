package com.example.dynamodb.cotacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

@Component
public class CotacaoDolar extends CotacaoAbastract implements Cotacao {

//    https://www.cepea.esalq.usp.br/br/consultas-ao-banco-de-dados-do-site.aspx?tabela_id=dolar&data_inicial=17%2F09%2F2019&periodicidade=1&data_final=17%2F09%2F2019
//{
//    com link download xls
//}

//        16092019;220;A;USD;4,0866;4,0872;1,0000;1,0000
//        17092019;220;A;USD;4,0992;4,0998;1,0000;1,0000
    @Override
    public CotacaoRegistro executar() {

        final List<String> listaLinhas = baixarArquivo(UrlColetoresEnum.DOLAR.getUrlTemplate(inicioFim()));

        return listaLinhas.stream().findFirst().map(str -> {
            final String[] colunas = str.split(";");
            if (colunas.length >= 4) {
                String cotacaoDolar = colunas[4];
                CotacaoRegistro cotacaoRegistro = new CotacaoRegistro();
                cotacaoRegistro.data = LocalDate.now();
                cotacaoRegistro.valor = Double.valueOf(cotacaoDolar.replace(",", "."));
                return cotacaoRegistro;
            }

            return null;

        }).orElse(null);
    }



//        try (Stream<String> stream = Files.lines(file.toPath())) {
//
//            stream.forEach(System.out::println);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        try {
//
//        BufferedReader reader = new BufferedReader(new FileReader(file));
//        String strCurrentLine;
//        while ((strCurrentLine = reader.readLine()) != null) {
//
//            System.out.println(strCurrentLine);
//        }
//        reader.close();
//
//    } catch (IOException e) {
//        e.printStackTrace();
//    }


//    File file = restTemplate.execute(url, HttpMethod.GET, null, clientHttpResponse -> {
//        final InputStream body = clientHttpResponse.getBody();
//        BufferedReader br = new BufferedReader(new InputStreamReader(body, StandardCharsets.UTF_8));
//
//        String strCurrentLine;
//        while ((strCurrentLine = br.readLine()) != null) {
//
//            System.out.println(strCurrentLine);
//        }
//        br.close();
//
//
//        File ret = File.createTempFile("download", "tmp");
//        StreamUtils.copy(body, new FileOutputStream(ret));
//        return ret;
//    });
}
