const AWS = require('aws-sdk');

AWS.config.update({region: 'us-east-1'});

const dynamodb = new AWS.DynamoDB()

dynamodb.createTable({
    TableName: "cotacoes",
    AttributeDefinitions: [
        {
            AttributeName: "dataCotacao",
            AttributeType: "S"
        }
        ],
    KeySchema: [
        {
            AttributeName: "dataCotacao",
            KeyType: "HASH"
        }
        ],
    ProvisionedThroughput: {
        ReadCapacityUnits: 1,
        WriteCapacityUnits: 1
    }
}, (err, data)=>{
    if(err) {
        console.log(err);
    } else {
        console.log(JSON.stringify(data, null, 2));
    }
});