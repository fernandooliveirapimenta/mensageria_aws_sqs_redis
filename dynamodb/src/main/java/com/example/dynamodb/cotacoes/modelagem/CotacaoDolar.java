package com.example.dynamodb.cotacoes.modelagem;

import java.util.ArrayList;
import java.util.List;

public class CotacaoDolar implements CotacaoColetor {
    @Override
    public List<Object> coletar(Object p) {
        return new ArrayList<>();
    }
}
