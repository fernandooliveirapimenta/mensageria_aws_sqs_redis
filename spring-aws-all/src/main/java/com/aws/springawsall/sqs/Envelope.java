package com.aws.springawsall.sqs;

import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Envelope <T> {

    private String uuid;
    private String data;
    private TipoEventoEnum tipoEventoEnum;
    private T payload;
}
