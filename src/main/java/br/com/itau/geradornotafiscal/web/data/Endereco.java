package br.com.itau.geradornotafiscal.web.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {
    @JsonProperty("cep")
    private String cep;

    @JsonProperty("logradouro")
    private String logradouro;

    @JsonProperty("numero")
    private String numero;

    @JsonProperty("estado")
    private String estado;

    @JsonProperty("complemento")
    private String complemento;

    @JsonProperty("finalidade")
    private Finalidade finalidade;

    @JsonProperty("regiao")
    private Regiao regiao;
}