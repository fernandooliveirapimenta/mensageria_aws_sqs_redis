package com.example.dynamodb;

import com.example.dynamodb.cotacoes.CotacaoAnp;
import com.example.dynamodb.cotacoes.CotacaoDolar;
import com.example.dynamodb.cotacoes.CotacaoMilho;
import com.example.dynamodb.cotacoes.Estados;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;

public class TesteAnp {


    public static void main(String[] args) throws Exception {


        File file = new File(
                Objects.requireNonNull(
                        CotacaoMilho.class
                                .getClassLoader()
                                .getResource("dieselAnp.xlsx")).getFile()
        );

//        System.out.println(Estados.porNome("ACRE"));
        CotacaoAnp anp = new CotacaoAnp();
        anp.lerXlsAnp(file, "tt");
    }
}
