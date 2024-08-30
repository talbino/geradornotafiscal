package br.com.itau.geradornotafiscal.service.impl;

import br.com.itau.geradornotafiscal.gateway.FreteGateway;
import br.com.itau.geradornotafiscal.model.Endereco;
import br.com.itau.geradornotafiscal.model.Finalidade;
import br.com.itau.geradornotafiscal.model.Pedido;
import br.com.itau.geradornotafiscal.model.Regiao;
import br.com.itau.geradornotafiscal.service.FreteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class FreteServiceImpl implements FreteService {

    private final FreteGateway freteGateway;

    @Override
    public BigDecimal calcularFrete(Pedido pedido) {
        final Regiao regiao = getRegiao(pedido);
        if (nonNull(regiao)) {
            BigDecimal percentualFrete = freteGateway.obterFretePorRegiao(regiao);
            return pedido.getValorFrete().multiply(percentualFrete);
        }
        return pedido.getValorFrete();
    }

    private Regiao getRegiao(Pedido pedido) {
        return pedido.getDestinatario().getEnderecos().stream()
                .filter(endereco -> endereco.getFinalidade() == Finalidade.ENTREGA || endereco.getFinalidade() == Finalidade.COBRANCA_ENTREGA)
                .map(Endereco::getRegiao)
                .findFirst()
                .orElse(null);
    }

}
