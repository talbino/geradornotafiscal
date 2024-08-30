package br.com.itau.geradornotafiscal.service;

import br.com.itau.geradornotafiscal.model.Pedido;

import java.math.BigDecimal;

public interface FreteService {

    BigDecimal calcularFrete(Pedido pedido);
}
