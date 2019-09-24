package com.example.dynamodb.event;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.concurrent.atomic.AtomicBoolean;

@Component
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ExecutorAS {

    private AtomicBoolean atomicBoolean = new AtomicBoolean(false);

    public void simularHttp(CustomSpringEvent event) {

        if(!atomicBoolean.get()) {
            atomicBoolean.set(true);
            System.out.println("Received spring custom" +
                    " event - " + event.getMessage());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicBoolean.set(false);
            System.out.println("terminou");
        } else {
            System.out.println("Ja esta executando");
        }

    }

}
