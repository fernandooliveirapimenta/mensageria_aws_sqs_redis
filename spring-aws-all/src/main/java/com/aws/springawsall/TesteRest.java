package com.aws.springawsall;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/aws")
public class TesteRest {

    @Value(value = "${fila}")
    private String fila;

    private final SqsQueueSender sqsQueueSender;

    public TesteRest(SqsQueueSender sqsQueueSender) {
        this.sqsQueueSender = sqsQueueSender;
    }

    @GetMapping
    public void teste() {

        // criando client apertir do ~/.aws/credencial

        AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();

        SendMessageRequest send_msg_request = new SendMessageRequest()
                .withQueueUrl(fila)
                .withMessageBody("hello world")
                .withDelaySeconds(5);
        sqs.sendMessage(send_msg_request);

    }

    //apartir do framework spring
    @GetMapping(value = "/2")
    public void teste2() {
        sqsQueueSender.send("tese ksjks lskdjksjgk");
    }

}
