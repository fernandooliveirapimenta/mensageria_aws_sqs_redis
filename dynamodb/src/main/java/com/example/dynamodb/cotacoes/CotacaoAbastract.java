package com.example.dynamodb.cotacoes;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpMethod;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

abstract class CotacaoAbastract {

     RestTemplate restTemplate = new RestTemplate();

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
            if(url.contains(".lerXlsCepea")) {
                return xls(arquivo);
            }
            return csv(arquivo);
        });
    }

    public InputStream baixarArquivoStream(String url) {
        return restTemplate.execute(url, HttpMethod.GET, null, HttpInputMessage::getBody);
    }

    private List<String> xls(InputStream arquivo) throws IOException {

        try {

            File ret = File.createTempFile("download", ".xsl");
            StreamUtils.copy(arquivo, new FileOutputStream(ret));
            Workbook wb = WorkbookFactory.create(ret);
            Sheet datatypeSheet = wb.getSheetAt(0);

            List<String> retorno = new ArrayList<>();

            final Row currentRow = datatypeSheet.getRow(4);
            for (Cell currentCell : currentRow) {
                retorno.add(currentCell.getStringCellValue());
            }

            return retorno;
        } catch (Exception e) {
            e.printStackTrace();
        }
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
