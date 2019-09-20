package com.example.dynamodb.cotacoes;

import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;

public class CotacaoAnp {

    public Map<String, Object> lerXlsAnp(File arquivo, String tipo) throws Exception {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MMM-yyyy");

        Workbook wb = WorkbookFactory.create(arquivo);
        Sheet p = wb.getSheetAt(0);


        boolean continuarLendo = true;
        int estadosLidos = 0;
        int linha = p.getLastRowNum();
        while (continuarLendo) {

            if(estadosLidos == 27)
                continuarLendo = false;

            final Row ultima = p.getRow(linha);
            final String produto = ultima.getCell(4).toString();
            if(produto.trim().toUpperCase().equals("ÓLEO DIESEL")) {

                System.out.print(LocalDate.parse(ultima.getCell(1).toString(), formatter).toString() + " ");
                final String estado = ultima.getCell(3).toString();
//                System.out.print(estado + " ");
                System.out.print(Estados.porNome(estado) + " ");
                System.out.print(ultima.getCell(10).toString() + " ");
                System.out.print(ultima.getCell(12).toString() + " ");
                System.out.println();
                estadosLidos ++;
            }

            linha--;


        }
//        final Row penultima = datatypeSheet.getRow(datatypeSheet.getLastRowNum() - 1);

        return null;
    }
}
