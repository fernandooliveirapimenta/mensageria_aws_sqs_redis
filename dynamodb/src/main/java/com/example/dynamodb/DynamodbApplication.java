package com.example.dynamodb;

import com.example.dynamodb.event.ExecutorAS;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@SpringBootApplication
public class DynamodbApplication {

    public static void main(String[] args) {
        SpringApplication.run(DynamodbApplication.class, args);
    }

    @Bean
    public RestTemplate customRestTemplate(RestTemplateBuilder builder) {

        return builder.setConnectTimeout(Duration.ofSeconds(10))
                .setReadTimeout(Duration.ofSeconds(10))
                .build();


    }

//    @Bean
//    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
//    public ExecutorAS executorAs() {
//        return new ExecutorAS();
//    }

}
