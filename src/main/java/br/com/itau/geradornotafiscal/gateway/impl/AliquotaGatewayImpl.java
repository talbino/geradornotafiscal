package br.com.itau.geradornotafiscal.gateway.impl;

import br.com.itau.geradornotafiscal.gateway.AliquotaGateway;
import br.com.itau.geradornotafiscal.gateway.data.pessoa.Aliquota;
import br.com.itau.geradornotafiscal.gateway.data.pessoa.AliquotaPessoaJuridicaStrategy;
import br.com.itau.geradornotafiscal.gateway.data.pessoa.fisica.AliquotaPessoaFisica;
import br.com.itau.geradornotafiscal.gateway.data.pessoa.juridica.AliquotaPessoaJuridica;
import br.com.itau.geradornotafiscal.model.RegimeTributacaoPJ;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@Component
@RequiredArgsConstructor
public class AliquotaGatewayImpl implements AliquotaGateway {


    @Override
    public BigDecimal obterAliquotaPessoaFisica(BigDecimal valor) {
        return obterAliquota(AliquotaPessoaFisica.getAliquotas() , valor);
    }


    @Override
    public BigDecimal obterAliquotaPessoaJuridica(RegimeTributacaoPJ regimeTributacaoPJ, BigDecimal valor) {
        Optional<AliquotaPessoaJuridica> aliquotaPessoaJuridicaOp =
                AliquotaPessoaJuridicaStrategy.getAliquotaPessoaJuridica(regimeTributacaoPJ);

        return aliquotaPessoaJuridicaOp
                .map(aliquotaPessoaJuridica -> obterAliquota(aliquotaPessoaJuridica.getAliquotas(), valor))
                .orElse(new BigDecimal("0.0"));
    }

    private BigDecimal obterAliquota(List<Aliquota> aliquotas, BigDecimal valor) {
        return aliquotas.stream().filter(a -> valor.compareTo(a.getValorInicial()) >= 0)
                .filter(a -> valor.compareTo(a.getValorFinal()) <= 0).map(Aliquota::getAliquota)
                .findFirst().orElse(new BigDecimal(0));
    }

}
