package com.example.dynamodb;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.model.*;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class DynamoTest {

    public DynamoTest(AmazonDynamoDB amazonDynamoDB) {
        this.amazonDynamoDB = amazonDynamoDB;
    }

    private final AmazonDynamoDB amazonDynamoDB;

    @GetMapping("/create")
    public String test() {
        DynamoDB dynamoDB = new DynamoDB(amazonDynamoDB);
        String tableName = "Movies";
        try {
            System.out.println("Attempting to create table; please wait...");
            Table table = dynamoDB.createTable(tableName,
                    Arrays.asList(new KeySchemaElement("year", KeyType.HASH), // Partition
                            // key
                            new KeySchemaElement("title", KeyType.RANGE)), // Sort key
                    Arrays.asList(new AttributeDefinition("year", ScalarAttributeType.N),
                            new AttributeDefinition("title", ScalarAttributeType.S)),
                    new ProvisionedThroughput(5L, 5L));
            table.waitForActive();
            System.out.println("Success. Table status: " +
                    table.getDescription().getTableStatus());
            return "succes";
        }
        catch (Exception e) {
            System.err.println("Unable to create table: ");
            System.err.println(e.getMessage());
            return "fail";
        }
    }


    @GetMapping("/load")
    public String load() throws IOException {
        DynamoDB dynamoDB = new DynamoDB(amazonDynamoDB);
        Table table = dynamoDB.getTable("Movies");
        JsonParser parser = new JsonFactory().createParser(new File("arquivo.json"));
        JsonNode rootNode = new ObjectMapper().readTree(parser);
        Iterator<JsonNode> iter = rootNode.iterator();
        ObjectNode currentNode;
        while (iter.hasNext()) {
            currentNode = (ObjectNode) iter.next();
            int year = currentNode.path("year").asInt();
            String title = currentNode.path("title").asText();
            try {
                table.putItem(new Item().withPrimaryKey("year", year, "title",
                        title).withJSON("info",
                        currentNode.path("info").toString()));
                System.out.println("PutItem succeeded: " + year + " " + title);
            }
            catch (Exception e) {
                System.err.println("Unable to add movie: " + year + " " + title);
                System.err.println(e.getMessage());
                return "fail";
            }
        }
        parser.close();
        return "success";
    }

    @GetMapping("criar")
    public PutItemResult criar(@RequestParam("year") int year,
                        @RequestParam("title") String title) {

        DynamoDB dynamoDB = new DynamoDB(amazonDynamoDB);

        Table table = dynamoDB.getTable("Movies");

        final Map<String, Object> info = new HashMap<>();
        info.put("plot", "Nothing happens at all.");
        info.put("rating", 0);

        final PutItemOutcome putItemOutcome = table.putItem(new Item().withPrimaryKey("year", year,
                "title", title).withMap("info", info));

        return putItemOutcome.getPutItemResult();
    }
}
