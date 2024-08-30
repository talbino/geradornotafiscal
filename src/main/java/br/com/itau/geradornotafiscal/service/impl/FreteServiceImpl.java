package br.com.itau.geradornotafiscal.service.impl;

import br.com.itau.geradornotafiscal.gateway.FreteGateway;
import br.com.itau.geradornotafiscal.model.*;
import br.com.itau.geradornotafiscal.service.FreteService;
import br.com.itau.geradornotafiscal.service.exception.ErroNegocioException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

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
        return Optional.of(pedido).map(Pedido::getDestinatario).map(Destinatario::getEnderecos)
                .filter(e -> !e.isEmpty()).orElseThrow(()-> new ErroNegocioException("pedido sem endereço")).stream()
                .filter(endereco -> endereco.getFinalidade() == Finalidade.ENTREGA || endereco.getFinalidade() == Finalidade.COBRANCA_ENTREGA)
                .map(Endereco::getRegiao)
                .findFirst()
                .orElseThrow(()-> new ErroNegocioException("pedido sem endereço"));
    }

}
