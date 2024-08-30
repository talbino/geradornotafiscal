package br.com.itau.geradornotafiscal.web.data.response;

import br.com.itau.geradornotafiscal.web.data.Destinatario;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class NotaFiscalResponse {
    @JsonProperty("id_nota_fiscal")
    private String idNotaFiscal;

    @JsonProperty("data")
    private LocalDateTime data;

    @JsonProperty("valor_total_itens")
    private BigDecimal valorTotalItens;

    @JsonProperty("valor_frete")
    private BigDecimal valorFrete;

    @JsonProperty("itens")
    private List<ItemNotaFiscalResponse> itens;

    @JsonProperty("destinatario")
    private Destinatario destinatario;

}