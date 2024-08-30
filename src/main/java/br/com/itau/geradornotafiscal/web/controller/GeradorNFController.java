package br.com.itau.geradornotafiscal.web.controller;


import br.com.itau.geradornotafiscal.web.data.request.PedidoRequest;
import br.com.itau.geradornotafiscal.web.data.response.NotaFiscalResponse;
import br.com.itau.geradornotafiscal.web.mapper.NotaFiscalMapper;
import br.com.itau.geradornotafiscal.web.mapper.PedidoMapper;
import lombok.RequiredArgsConstructor;
import br.com.itau.geradornotafiscal.model.Pedido;
import br.com.itau.geradornotafiscal.service.GeradorNotaFiscalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pedido")
@RequiredArgsConstructor
public class GeradorNFController {


	private final GeradorNotaFiscalService notaFiscalService;
	private final NotaFiscalMapper notaFiscalMapper;
	private final PedidoMapper pedidoMapper;

	@PostMapping("/gerarNotaFiscal")
	public ResponseEntity<NotaFiscalResponse> gerarNotaFiscal(@RequestBody PedidoRequest pedidoRequest) {

		Pedido pedido = pedidoMapper.pedidoRequestToPedido(pedidoRequest);
		NotaFiscalResponse notaFiscal = notaFiscalMapper.notaFiscalToNotaFiscalResponse(notaFiscalService.gerarNotaFiscal(pedido));
		return new ResponseEntity<>(notaFiscal, HttpStatus.OK);
	}


}
