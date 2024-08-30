package br.com.itau.geradornotafiscal.web.data.request;

import br.com.itau.geradornotafiscal.web.data.Destinatario;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Builder
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class PedidoRequest {
	    @JsonProperty("id_pedido")
	    private int idPedido;

	    @JsonProperty("data")
	    private LocalDate data;

	    @JsonProperty("valor_total_itens")
	    private BigDecimal valorTotalItens;

	    @JsonProperty("valor_frete")
	    private BigDecimal valorFrete;

	    @JsonProperty("itens")
	    private List<ItemRequest> itens;

	    @JsonProperty("destinatario")
	    private Destinatario destinatario;

}
