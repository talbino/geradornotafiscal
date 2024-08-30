package br.com.itau.geradornotafiscal.gateway.impl;

import br.com.itau.geradornotafiscal.gateway.FreteGateway;
import br.com.itau.geradornotafiscal.gateway.data.frete.FreteRegiaoEnum;
import br.com.itau.geradornotafiscal.model.Regiao;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class FreteGatewayImpl implements FreteGateway {

    @Override
    public BigDecimal obterFretePorRegiao(Regiao regiao) {
        return FreteRegiaoEnum.obterPercentualFrete(regiao.name());
    }

}
