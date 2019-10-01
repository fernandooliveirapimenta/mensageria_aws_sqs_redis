package com.example.dynamodb.zarc;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

// todos os dados com upper case

@DynamoDBTable(tableName = "srmto_zarc")
public class ZarcModel {

    @DynamoDBHashKey
    private String chave; //ANOSAFRA#CULTURA#UF#MUNICIPI#GRUPO#SOLO pattern dessa modelagem
    private ZarcDetalhamentoModel valor;
}
