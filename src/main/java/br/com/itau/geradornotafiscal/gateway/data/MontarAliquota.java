package br.com.itau.geradornotafiscal.gateway.data;

import br.com.itau.geradornotafiscal.gateway.data.pessoa.Aliquota;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
public final class MontarAliquota {

    private List<Aliquota> aliquotas = new ArrayList<>();

    private MontarAliquota(){
    }

    public static MontarAliquota montar(){
       return new MontarAliquota();
    }

    public MontarAliquota add(double valorInicial, double valorFinal, double aliquota) {
        aliquotas.add(new Aliquota(doubleToBigDecimal(valorInicial),
                                   doubleToBigDecimal(valorFinal),
                                   doubleToBigDecimal(aliquota)));
        return this;
    }

    private BigDecimal doubleToBigDecimal(double valor) {
        return BigDecimal.valueOf(valor);
    }

}
