const AWS = require("aws-sdk");
AWS.config.update({ region: 'us-east-1' });

const docClient = new AWS.DynamoDB.DocumentClient();

// docClient.put({
//     TableName: 'td_notes_sdk',
//     Item: {
//         user_id: 'bb',
//         timestamp: 2,
//         title: 'changed title',
//         content: 'changed content'
//     }
// }, (err, data)=>{
//     if(err) {
//         console.log(err);
//     } else {
//         console.log(data);
//     }
// });

// docClient.update({
//     TableName: 'td_notes_sdk',
//     Key: {
//         user_id: 'bb',
//         timestamp: 1
//     },
//     UpdateExpression: 'set #t = :t',
//     ExpressionAttributeNames: {
//         '#t': 'title'
//     },
//     ExpressionAttributeValues: {
//         ':t': "Updated title"
//     }
// }, (err, data)=>{
//     if(err) {
//         console.log(err);
//     } else {
//         console.log(data);
//     }
// });

// docClient.delete({
//     TableName: 'td_notes_sdk',
//     Key: {
//         user_id: 'bb',
//         timestamp: 1
//     }
// }, (err, data)=>{
//     if(err) {
//         console.log(err);
//     } else {
//         console.log(data);
//     }
// });


docClient.batchWrite({
    RequestItems: {
        'cotacoes': [
            {
                PutRequest: {
                    Item: {
                        dataCotacao: '2019-09-19',
                        valores: [
                            {
                                tipo: "DOLAR",
                                valor: 4.33,
                                variacao: 1.3
                            },
                            {
                                tipo: "MILHO_BOLSA",
                                valor: 4.33,
                                variacao: 1.3
                            },
                            {
                                tipo: "SOJA_CEPEA",
                                valor: 37.33,
                                variacao: 1.3
                            },
                        ]
                    }
                }
            },
            {
                PutRequest: {
                    Item: {
                        dataCotacao: '2019-09-18',
                        valores: [
                            {
                                tipo: "DOLAR",
                                valor: 4.33,
                                variacao: 1.3
                            },
                            {
                                tipo: "MILHO_BOLSA",
                                valor: 4.33,
                                variacao: 1.3
                            },
                            {
                                tipo: "SOJA_CEPEA",
                                valor: 37.33,
                                variacao: 1.3
                            },
                        ]
                    }
                }
            },
            {
                PutRequest: {
                    Item: {
                        dataCotacao: '2019-09-17',
                        valores: [
                            {
                                tipo: "DOLAR",
                                valor: 4.33,
                                variacao: 1.3
                            },
                            {
                                tipo: "MILHO_BOLSA",
                                valor: 4.33,
                                variacao: 1.3
                            },
                            {
                                tipo: "SOJA_CEPEA",
                                valor: 37.33,
                                variacao: 1.3
                            },
                        ]
                    }
                }
            },
            {
                PutRequest: {
                    Item: {
                        dataCotacao: '2019-09-16',
                        valores: [
                            {
                                tipo: "DOLAR",
                                valor: 4.33,
                                variacao: 1.3
                            },
                            {
                                tipo: "MILHO_BOLSA",
                                valor: 4.33,
                                variacao: 1.3
                            },
                            {
                                tipo: "SOJA_CEPEA",
                                valor: 37.33,
                                variacao: 1.3
                            },
                        ]
                    }
                }
            }
            
        ]
    }
}, (err, data)=>{
    if(err) {
        console.log(err);
    } else {
        console.log(data);
    }
});