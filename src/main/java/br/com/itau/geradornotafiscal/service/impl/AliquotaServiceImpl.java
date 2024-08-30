package br.com.itau.geradornotafiscal.service.impl;


import br.com.itau.geradornotafiscal.gateway.AliquotaGateway;
import br.com.itau.geradornotafiscal.model.RegimeTributacaoPJ;
import br.com.itau.geradornotafiscal.service.AliquotaService;
import br.com.itau.geradornotafiscal.service.exception.ErroNegocioException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

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
        RegimeTributacaoPJ regimeTributacaoPJ1 = Optional.ofNullable(regimeTributacaoPJ).orElseThrow(()-> new ErroNegocioException("regime tributação me branco"));
        return aliquotaGateway.obterAliquotaPessoaJuridica(regimeTributacaoPJ1, valor);
    }
}
