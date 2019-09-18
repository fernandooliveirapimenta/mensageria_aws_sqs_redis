package com.example.dynamodb;

import com.example.dynamodb.cotacoes.CotacaoDolar;
import com.example.dynamodb.cotacoes.CotacaoMilho;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class TesteBacen {


    public static void main(String[] args) throws Exception {

        InputStream dolar = CotacaoDolar.class
            .getClassLoader().getResourceAsStream("dolarBacen.csv");

        CotacaoDolar cotacaoDolar = new CotacaoDolar();
        final List<String> csv = cotacaoDolar.csv(dolar);
        csv.forEach(System.out::println);
    }
}
