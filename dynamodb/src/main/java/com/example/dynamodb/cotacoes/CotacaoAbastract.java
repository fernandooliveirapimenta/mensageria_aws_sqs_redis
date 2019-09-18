package com.example.dynamodb.cotacoes;

import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

abstract class CotacaoAbastract {

    private RestTemplate restTemplate = new RestTemplate();

    public Object[] inicioFim() {
        final LocalDate now = LocalDate.now();
        final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        final String ontem = now.minusDays(1).format(fmt);
        final String hoje = now.format(fmt);
        return new Object[]{ontem, hoje};
    }

    public List<String> baixarArquivo(String url) {
        if(url == null) {
            return new ArrayList<>();
        }
        return restTemplate.execute(url, HttpMethod.GET, null, clientHttpResponse -> {
            final InputStream arquivo = clientHttpResponse.getBody();
            if(url.contains(".xls")) {
                return xls(arquivo);
            }
            return csv(arquivo);
        });
    }

    private List<String> xls(InputStream arquivo) throws IOException {
       return new ArrayList<>();
    }

    private List<String> csv(InputStream arquivo) throws IOException {
        BufferedReader leitor = new BufferedReader(new InputStreamReader(arquivo, StandardCharsets.UTF_8));
        List<String> linhas = new ArrayList<>();
        String linhaAtual;
        while ((linhaAtual = leitor.readLine()) != null) {
            linhas.add(linhaAtual);
        }
        leitor.close();
        return linhas;
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }
}
