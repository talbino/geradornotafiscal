package br.com.itau.geradornotafiscal.gateway;

import br.com.itau.geradornotafiscal.model.Regiao;

import java.math.BigDecimal;

public interface FreteGateway {

    BigDecimal obterFretePorRegiao(Regiao regiao);
}
