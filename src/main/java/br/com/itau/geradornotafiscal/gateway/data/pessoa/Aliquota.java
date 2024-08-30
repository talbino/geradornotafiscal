package br.com.itau.geradornotafiscal.gateway.data.pessoa;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Getter
@RequiredArgsConstructor
public class Aliquota {

    private final BigDecimal valorInicial;
    private final BigDecimal valorFinal;
    private final BigDecimal aliquota;
}
