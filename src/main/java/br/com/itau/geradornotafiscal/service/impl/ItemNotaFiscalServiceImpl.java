package br.com.itau.geradornotafiscal.service.impl;


import br.com.itau.geradornotafiscal.model.Destinatario;
import br.com.itau.geradornotafiscal.model.Item;
import br.com.itau.geradornotafiscal.model.ItemNotaFiscal;
import br.com.itau.geradornotafiscal.model.Pedido;
import br.com.itau.geradornotafiscal.service.CalcularAliquotaFactory;
import br.com.itau.geradornotafiscal.service.CalcularAliquotaService;
import br.com.itau.geradornotafiscal.service.ItemNotaFiscalService;
import br.com.itau.geradornotafiscal.service.exception.ErroNegocioException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class ItemNotaFiscalServiceImpl implements ItemNotaFiscalService {

    private final CalcularAliquotaFactory calcularAliquotaFactory;

    public List<ItemNotaFiscal> gerarItensNotaFiscal(Pedido pedido) {

        Optional.of(pedido).map(Pedido::getDestinatario)
                .map(Destinatario::getTipoPessoa)
                .orElseThrow(()-> new ErroNegocioException("tipo de pessoa não veio preenchido"));

        final CalcularAliquotaService calcularAliquotaService = calcularAliquotaFactory.getCalcularAliquotaService(pedido);
        BigDecimal aliquotaPercentual = calcularAliquotaService.calcularAliquota(pedido);

        return Optional.of(pedido)
                .map(Pedido::getItens)
                .filter(itens -> !itens.isEmpty())
                .orElseThrow(()-> new ErroNegocioException("pedido deve ter itens"))
                .stream()
                .map(item -> gerarItemNotaFiscal(item, aliquotaPercentual))
                .collect(Collectors.toList());

    }

    private ItemNotaFiscal gerarItemNotaFiscal(Item item, BigDecimal aliquotaPercentual) {
        BigDecimal valorUnitario = getValorUnitario(item);

        BigDecimal valorTributo = aliquotaPercentual.multiply(valorUnitario);
        return ItemNotaFiscal.builder()
                .idItem(item.getIdItem())
                .descricao(item.getDescricao())
                .valorUnitario(item.getValorUnitario())
                .quantidade(item.getQuantidade())
                .valorTributoItem(valorTributo)
                .build();
    }

    private BigDecimal getValorUnitario(Item item) {
        return Optional.ofNullable(item)
                .filter(i -> nonNull(i.getValorUnitario()))
                .map(Item::getValorUnitario)
                .orElseThrow(() -> new ErroNegocioException("item sem valor unitário"));
    }

}



