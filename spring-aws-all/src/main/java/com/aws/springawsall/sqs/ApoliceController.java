package com.aws.springawsall.sqs;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(path = "/apolice")
@RequiredArgsConstructor
public class ApoliceController {

    private final SqsProducer sqsProducer;

    @PostMapping
    public Apolice salvar(@RequestBody Apolice apolice) {
        final Apolice build = apolice.toBuilder().id(UUID.randomUUID().toString()).build();
        log.info("criando: "+build);
        sqsProducer.producerApolice(build);
        return build;
    }

}
