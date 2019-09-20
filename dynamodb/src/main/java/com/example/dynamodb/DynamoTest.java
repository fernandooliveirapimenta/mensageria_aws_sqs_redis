package com.example.dynamodb;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.document.internal.IteratorSupport;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.model.*;
import com.example.dynamodb.cotacoes.CotacaoDolar;
import com.example.dynamodb.cotacoes.CotacaoMilho;
import com.example.dynamodb.cotacoes.CotacaoValorModel;
import com.example.dynamodb.cotacoes.CotacoesModel;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/test")
public class DynamoTest {


    private final AmazonDynamoDB amazonDynamoDB;
    private final DynamoDBMapper dynamoDBMapper;
    private final CotacaoDolar cotacaoCepea;
    private final CotacaoMilho cotacaoMilho;

    public DynamoTest(AmazonDynamoDB amazonDynamoDB, DynamoDBMapper dynamoDBMapper, CotacaoDolar cotacaoCepea, CotacaoMilho cotacaoMilho) {
        this.amazonDynamoDB = amazonDynamoDB;
        this.dynamoDBMapper = dynamoDBMapper;
        this.cotacaoCepea = cotacaoCepea;
        this.cotacaoMilho = cotacaoMilho;
    }

    @GetMapping("/cotacao")
    public void  cotacao(@RequestParam String dt) {
//        cotacaoCepea.executar();
//        cotacaoMilho.executar();

        CotacoesModel c1 = new CotacoesModel();
        c1.setDataCotacao(dt+"dd");

        CotacoesModel c2 = new CotacoesModel();
        c2.setDataCotacao(LocalDate.now().minusDays(8).toString());

        CotacoesModel c3 = new CotacoesModel();
        c3.setDataCotacao("inexisteste");


        final Map<String, List<Object>> stringListMap = dynamoDBMapper.batchLoad(Arrays.asList(c1, c2, c3));

        final List<Object> cot = stringListMap.get("cotacoes");
        if(cot != null) {
            if(cot.size() > 1) {
                System.out.println("tenho backup");
                //percorre resultados e tendo certeza que foi ou nao
            } else if (cot.size() == 1){
                System.out.println("sem backup");
            } else {
                //coletafull e com retorno salvar no dynamo
                System.out.println("sem nada");
            }
            cot.forEach(obj -> {

                CotacoesModel m = (CotacoesModel) obj;

                final List<CotacaoValorModel> soja_cepea = m.filtrarPorTipo("SOJA_CEPEA");
                System.out.println(soja_cepea);
            });
        }



//        final CotacoesModel load =
//                dynamoDBMapper.load(CotacoesModel.class, dt);
//
//        System.out.println("valor");
//        System.out.println(load);
//
//        if(load != null) {
//            load.getValores().get(0).setValor(5000.7);
//            CotacaoValorModel cotacaoValorModel = new CotacaoValorModel();
//            cotacaoValorModel.setValor(33.3);
//            cotacaoValorModel.setTipo("DIESEL");
//            cotacaoValorModel.setUf("NOVO");
//            cotacaoValorModel.setVariacao(1.3);
//            load.getValores().add(cotacaoValorModel);
//            dynamoDBMapper.save(load);
//        }



    }

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

    @GetMapping("/{year}")
    public Iterator<Item> buscarPorAno(@PathVariable int year){
        DynamoDB dynamoDB = new DynamoDB(amazonDynamoDB);
        final Table movies = dynamoDB.getTable("Movies");

        Map<String, String> nameMap = new HashMap<>();
        nameMap.put("#yr", "year");

        Map<String, Object> valueMap = new HashMap<>();
        valueMap.put(":yyyy", year);

        QuerySpec querySpec = new QuerySpec()
                .withKeyConditionExpression("#yr = :yyyy")
                .withNameMap(nameMap)
                .withValueMap(valueMap);

        final ItemCollection<QueryOutcome> query = movies.query(querySpec);

        final IteratorSupport<Item, QueryOutcome> iterator = query.iterator();
        while (iterator.hasNext()) {
            final Item item = iterator.next();
            System.out.println(item.getNumber("year") + ": " +
                    item.getString("title") + " " + item.getMap("info"));
        }
        return null;
    }

    @GetMapping("/get/{year}")
    public Movie getMovie(@PathVariable int year) {
        final Movie load = dynamoDBMapper.load(Movie.class, year);
        return load;
    }

    @GetMapping("/saveItem")
    public void saveItem() {
        CatalogItem item = new CatalogItem();
        item.setId(102);
        item.setTitle("Book 102 Title");
        item.setISBN("222-2222222222");
        item.setBookAuthors(new HashSet<>(Arrays.asList("Author 1", "Author 2")));
        item.setSomeProp("Test");
        dynamoDBMapper.save(item);
    }

    @GetMapping("/getItem/{id}")
    public CatalogItem getItem(@PathVariable Integer id){
       return dynamoDBMapper.load(CatalogItem.class, id);
    }

}
