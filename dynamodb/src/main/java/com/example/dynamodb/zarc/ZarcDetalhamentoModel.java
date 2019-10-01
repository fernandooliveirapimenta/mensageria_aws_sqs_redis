package com.example.dynamodb.zarc;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;

import java.util.Map;


@DynamoDBDocument
public class ZarcDetalhamentoModel {

    private String anoSafra; //ex : 2019/2020
    private String cultura; // ex: SOJA ou MILHO
    private String uf; // ex: TO ou SP
    private String municipio; // ex: ABARÉ
    private String grupo; // GRUPO I
    private String solo; // ARGILOSO
    private Map<Integer, Integer> indicadorRelogio; /*
    Ex:

    1 40
    2 40
    3 40
    4 20
    5 0

    */
    

}
