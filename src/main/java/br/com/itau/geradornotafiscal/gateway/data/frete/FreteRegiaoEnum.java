package br.com.itau.geradornotafiscal.gateway.data.frete;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum FreteRegiaoEnum {

    NORTE(1.08f),
    NORDESTE(1.085f),
    CENTRO_OESTE(1.07f),
    SUDESTE(1.048f),
    SUL(1.06f);

    private final float percentualFrete;

    public static BigDecimal obterPercentualFrete(String regiao){
        float percentualFrete = Arrays.stream(values()).filter(f -> f.name().equalsIgnoreCase(regiao))
                .map(FreteRegiaoEnum::getPercentualFrete).findFirst().orElse(0f);
        return new BigDecimal(percentualFrete);
    }

}
