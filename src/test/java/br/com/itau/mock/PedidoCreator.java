package br.com.itau.mock;

import br.com.itau.geradornotafiscal.model.*;

import java.math.BigDecimal;
import java.util.List;

public class PedidoCreator {

    public static Pedido criarPedido(TipoPessoa tipoPessoa, Finalidade finalidade , Regiao regiao) {
        Pedido pedido = new Pedido();
        pedido.setValorTotalItens(new BigDecimal(600));
        pedido.setValorFrete(new BigDecimal(100));
        Destinatario destinatario = new Destinatario();
        destinatario.setTipoPessoa(tipoPessoa);
        criarEndereco(destinatario, regiao, finalidade);
        pedido.setDestinatario(destinatario);
        Item item = criarItem();
        pedido.setItens(List.of(item));
        return pedido;
    }

    private static Item criarItem() {
        Item item = new Item();
        item.setValorUnitario(new BigDecimal(100));
        item.setQuantidade(4);
        return item;
    }

    private static void criarEndereco(Destinatario destinatario, Regiao regiao, Finalidade finalidade) {
        Endereco endereco = new Endereco();
        endereco.setFinalidade(finalidade);
        endereco.setRegiao(regiao);
        destinatario.setEnderecos(List.of(endereco));
    }
}
