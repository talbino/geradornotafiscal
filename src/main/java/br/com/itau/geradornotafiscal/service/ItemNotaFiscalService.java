package br.com.itau.geradornotafiscal.service;

import br.com.itau.geradornotafiscal.model.ItemNotaFiscal;
import br.com.itau.geradornotafiscal.model.Pedido;

import java.util.List;

public interface ItemNotaFiscalService {

    List<ItemNotaFiscal> gerarItensNotaFiscal(Pedido pedido);
}
