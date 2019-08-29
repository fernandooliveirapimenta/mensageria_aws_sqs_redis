package com.aws.springawsall;

import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @SqsListener("pedidosFeeder")
    public void queueListener(String msg) {

        System.out.println(msg);
    }
}
