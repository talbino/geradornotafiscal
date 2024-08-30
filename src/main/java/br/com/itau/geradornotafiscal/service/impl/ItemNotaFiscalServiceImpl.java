package br.com.itau.geradornotafiscal.service.impl;


import br.com.itau.geradornotafiscal.model.Item;
import br.com.itau.geradornotafiscal.model.ItemNotaFiscal;
import br.com.itau.geradornotafiscal.model.Pedido;
import br.com.itau.geradornotafiscal.service.CalcularAliquotaFactory;
import br.com.itau.geradornotafiscal.service.CalcularAliquotaService;
import br.com.itau.geradornotafiscal.service.ItemNotaFiscalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemNotaFiscalServiceImpl implements ItemNotaFiscalService {

    private final CalcularAliquotaFactory calcularAliquotaFactory;

    public List<ItemNotaFiscal> gerarItensNotaFiscal(Pedido pedido) {

        final CalcularAliquotaService calcularAliquotaService = calcularAliquotaFactory.getCalcularAliquotaService(pedido);
        BigDecimal aliquotaPercentual = calcularAliquotaService.calcularAliquota(pedido);

        return Optional.of(pedido)
                .map(Pedido::getItens)
                .orElse(new ArrayList<>())
                .stream()
                .map(item -> gerarItemNotaFiscal(item, aliquotaPercentual))
                .collect(Collectors.toList());

    }

    private ItemNotaFiscal gerarItemNotaFiscal(Item item, BigDecimal aliquotaPercentual) {
        BigDecimal valorTributo = aliquotaPercentual.multiply(item.getValorUnitario());
        return ItemNotaFiscal.builder()
                .idItem(item.getIdItem())
                .descricao(item.getDescricao())
                .valorUnitario(item.getValorUnitario())
                .quantidade(item.getQuantidade())
                .valorTributoItem(valorTributo)
                .build();
    }

}



