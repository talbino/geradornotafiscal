package br.com.itau.geradornotafiscal.service.impl;


import br.com.itau.geradornotafiscal.gateway.AliquotaGateway;
import br.com.itau.geradornotafiscal.model.RegimeTributacaoPJ;
import br.com.itau.geradornotafiscal.service.AliquotaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AliquotaServiceImpl implements AliquotaService {

    private final AliquotaGateway aliquotaGateway;

    @Override
    public BigDecimal obterAliquotaPessoaFisica(BigDecimal valor) {
        return aliquotaGateway.obterAliquotaPessoaFisica(valor);
    }

    @Override
    public BigDecimal obterAliquotaPessoaJuridica(RegimeTributacaoPJ regimeTributacaoPJ, BigDecimal valor) {
        return aliquotaGateway.obterAliquotaPessoaJuridica(regimeTributacaoPJ, valor);
    }
}
