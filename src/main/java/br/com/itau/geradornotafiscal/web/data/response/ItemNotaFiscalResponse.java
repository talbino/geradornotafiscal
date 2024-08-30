package br.com.itau.geradornotafiscal.web.data.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ItemNotaFiscalResponse {
    @JsonProperty("id_item")
    private String idItem;

    @JsonProperty("descricao")
    private String descricao;

    @JsonProperty("valor_unitario")
    private BigDecimal valorUnitario;

    @JsonProperty("quantidade")
    private int quantidade;

    @JsonProperty("valor_tributo_item")
    private BigDecimal valorTributoItem;

}