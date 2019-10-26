package com.aws.springawsall.sqs;

import lombok.*;

import java.util.List;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Apolice {
    private String id;
    private Double valor;
    private List<String> tags;
}
