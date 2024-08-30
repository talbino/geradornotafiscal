package br.com.itau.geradornotafiscal.service.impl;

import br.com.itau.geradornotafiscal.model.Pedido;
import br.com.itau.geradornotafiscal.service.AliquotaService;
import br.com.itau.geradornotafiscal.service.CalcularAliquotaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;


@Component
@RequiredArgsConstructor
public class CalcularAliquotaPessoaJuridicaServiceImpl implements CalcularAliquotaService {

    private final AliquotaService aliquotaService;

    @Override
    public BigDecimal calcularAliquota(Pedido pedido) {
        return aliquotaService.obterAliquotaPessoaJuridica(pedido.getDestinatario().getRegimeTributacao(), pedido.getValorTotalItens());
    }

}
