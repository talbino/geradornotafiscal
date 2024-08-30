package br.com.itau.geradornotafiscal.web.data.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErroResponse {
    private String codigo;
    private String mensagem;
}
