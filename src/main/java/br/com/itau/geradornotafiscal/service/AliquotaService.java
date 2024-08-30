package br.com.itau.geradornotafiscal.service;

import br.com.itau.geradornotafiscal.model.RegimeTributacaoPJ;

import java.math.BigDecimal;

public interface AliquotaService {

    BigDecimal obterAliquotaPessoaFisica(BigDecimal valor);

    BigDecimal obterAliquotaPessoaJuridica(RegimeTributacaoPJ regimeTributacaoPJ, BigDecimal valor);
}
