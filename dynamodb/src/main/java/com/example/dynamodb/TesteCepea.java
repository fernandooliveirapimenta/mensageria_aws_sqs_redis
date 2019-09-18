package com.example.dynamodb;

import com.example.dynamodb.cotacoes.CotacaoMilho;

import java.io.File;
import java.util.Map;
import java.util.Objects;

public class TesteCepea {


    public static void main(String[] args) throws Exception {
        File fileBovespa = new File(
                Objects.requireNonNull(CotacaoMilho.class.getClassLoader().getResource("milhoBovespa.xls")).getFile()
        );
        File fileCepea = new File(
                Objects.requireNonNull(CotacaoMilho.class.getClassLoader().getResource("milhoCepea.xls")).getFile()
        );
        File sojaBovespa = new File(
                Objects.requireNonNull(CotacaoMilho.class.getClassLoader().getResource("sojaBovespa.xls")).getFile()
        );
        File sojaCepea = new File(
                Objects.requireNonNull(CotacaoMilho.class.getClassLoader().getResource("sojaCepea.xls")).getFile()
        );
//        InputStream inputStream = CotacaoMilho.class
//                .getClassLoader().getResourceAsStream("database.properties");
        CotacaoMilho milho = new CotacaoMilho();
        final Map<String, Object> xls = milho.lerXlsCepea(fileBovespa, "MILHO_BOVESPA");
        System.out.println(xls);

        final Map<String, Object> xls2 = milho.lerXlsCepea(fileCepea, "MILHO_MEDIA");
        System.out.println(xls2);

        final Map<String, Object> xls3 = milho.lerXlsCepea(sojaBovespa, "SOJA_BOVESPA");
        System.out.println(xls3);

        final Map<String, Object> xls4 = milho.lerXlsCepea(sojaCepea, "SOJA_MEDIA");
        System.out.println(xls4);
    }
}
