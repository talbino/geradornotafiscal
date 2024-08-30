package br.com.itau.calculadoratributos;

import br.com.itau.geradornotafiscal.model.*;
import br.com.itau.geradornotafiscal.service.impl.*;
import br.com.itau.mock.PedidoCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class GeradorNotaFiscalServiceImplTest {

    @InjectMocks
    private GeradorNotaFiscalServiceImpl geradorNotaFiscalService;

    @Mock
    private ItemNotaFiscalServiceImpl itemNotaFiscalService;

    @Mock
    private FreteServiceImpl freteService;


    @Mock
    private EstoqueServiceImpl estoqueService;

    @Mock
    private RegistroServiceImpl registroService;

    @Mock
    private EntregaServiceImpl entregaService;

    @Mock
    private FinanceiroServiceImpl financeiroService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }



    @Test
    @DisplayName("deve gerar nota fiscal com valor total itens igual ao do pedido e destinatario também")
    public void deveGerarNotaFiscal() {

        Pedido pedido = PedidoCreator.criarPedido(TipoPessoa.FISICA, Finalidade.ENTREGA, Regiao.SUDESTE);

        ItemNotaFiscal itemNotaFiscal = new ItemNotaFiscal();
        itemNotaFiscal.setValorUnitario(new BigDecimal(100));
        itemNotaFiscal.setQuantidade(4);

        when(freteService.calcularFrete(any())).thenReturn(new BigDecimal(10));
        when(itemNotaFiscalService.gerarItensNotaFiscal(any())).thenReturn(List.of(itemNotaFiscal));

        NotaFiscal notaFiscal = geradorNotaFiscalService.gerarNotaFiscal(pedido);
        assertEquals(pedido.getValorTotalItens(), notaFiscal.getValorTotalItens());
        assertEquals(pedido.getDestinatario(), notaFiscal.getDestinatario());

    }


}