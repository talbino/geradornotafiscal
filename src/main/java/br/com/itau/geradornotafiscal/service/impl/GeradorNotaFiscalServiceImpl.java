package br.com.itau.geradornotafiscal.service.impl;

import br.com.itau.geradornotafiscal.model.*;
import br.com.itau.geradornotafiscal.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GeradorNotaFiscalServiceImpl implements GeradorNotaFiscalService{


	private final ItemNotaFiscalServiceImpl itemNotaFiscalService;
	private final FreteService freteService;
	private final EstoqueService estoqueService;
	private final RegistroService registroService;
	private final EntregaService entregaService;
	private final FinanceiroServiceImpl financeiroService;

	@Override
	public NotaFiscal gerarNotaFiscal(Pedido pedido) {

		List<ItemNotaFiscal> itemNotaFiscalList = itemNotaFiscalService.gerarItensNotaFiscal(pedido);
		BigDecimal valorFreteComPercentual = freteService.calcularFrete(pedido);
		NotaFiscal notaFiscal = criarNotaFiscal(pedido, valorFreteComPercentual, itemNotaFiscalList);

		estoqueService.enviarNotaFiscalParaBaixaEstoque(notaFiscal);
		registroService.registrarNotaFiscal(notaFiscal);
		entregaService.agendarEntrega(notaFiscal);
		financeiroService.enviarNotaFiscalParaContasReceber(notaFiscal);
		return notaFiscal;
	}

	private NotaFiscal criarNotaFiscal(Pedido pedido,
									   BigDecimal valorFreteComPercentual,
									   List<ItemNotaFiscal> itemNotaFiscalList) {

		String idNotaFiscal = UUID.randomUUID().toString();
		return  NotaFiscal.builder()
				.idNotaFiscal(idNotaFiscal)
				.data(LocalDateTime.now())
				.valorTotalItens(pedido.getValorTotalItens())
				.valorFrete(valorFreteComPercentual.setScale(2, RoundingMode.HALF_UP))
				.itens(itemNotaFiscalList)
				.destinatario(pedido.getDestinatario())
				.build();
	}

}