package br.com.itau.geradornotafiscal.gateway;


import br.com.itau.geradornotafiscal.model.RegimeTributacaoPJ;

import java.math.BigDecimal;

public interface AliquotaGateway {

    BigDecimal obterAliquotaPessoaFisica(BigDecimal valor);

    BigDecimal obterAliquotaPessoaJuridica(RegimeTributacaoPJ regimeTributacaoPJ, BigDecimal valor);

}
