package com.example.dynamodb.event;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Scope;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class CustomSpringEventListener {

    private final ExecutorAS executorAs;

    public CustomSpringEventListener(ExecutorAS executorAs) {
        this.executorAs = executorAs;
    }

    @Async
    @EventListener
    public void onApplicationEvent(CustomSpringEvent event) {
        executorAs.simularHttp(event);

    }
}