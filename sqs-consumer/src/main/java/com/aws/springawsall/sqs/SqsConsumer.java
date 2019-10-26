package com.aws.springawsall.sqs;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SqsConsumer {

    @SqsListener(value = "${filaApolice}", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void filaApoliceListener(Envelope<Object> envelope) {
        System.out.println("listener");
        System.out.println(envelope);
    }

}
