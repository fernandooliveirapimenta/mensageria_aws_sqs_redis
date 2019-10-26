package com.aws.springawsall.sqs;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SqsProducer {

    private final QueueMessagingTemplate queueMessagingTemplate;
    private final String filaApolice;

    public void producerApolice(Apolice apolice) {
        Envelope<Object> envelope = Envelope
                .builder()
                .uuid(UUID.randomUUID().toString())
                .data(LocalDateTime.now().toString())
                .payload(apolice)
                .tipoEventoEnum(TipoEventoEnum.INSERT)
                .build();
       this.queueMessagingTemplate.convertAndSend(filaApolice, envelope);
    }

}
