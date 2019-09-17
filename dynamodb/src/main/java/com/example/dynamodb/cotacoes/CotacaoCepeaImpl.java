package com.example.dynamodb.cotacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RestTemplate;

import java.io.*;

@Component(value = "cepea")
public class CotacaoCepeaImpl implements Cotacao {

    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public CotacaoRegistro executar() {
//        ResponseEntity<byte[]> response = restTemplate.exchange(
//                "https://www.google.com/assets/images/srpr/logo11w.png",
//                HttpMethod.GET, entity, byte[].class, "1");
        File file = restTemplate.execute("http://repositorio.ipea.gov.br/bitstream/11058/3532/9/cc13_serieshistoricas.xls", HttpMethod.GET, null, clientHttpResponse -> {
            File ret = File.createTempFile("download", "tmp");
            StreamUtils.copy(clientHttpResponse.getBody(), new FileOutputStream(ret));
            return ret;
        });

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String strCurrentLine;
            while ((strCurrentLine = reader.readLine()) != null) {

                System.out.println(strCurrentLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(file);
        return null;
    }
}
