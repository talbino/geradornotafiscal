package br.com.itau.calculadoratributos;


import br.com.itau.geradornotafiscal.gateway.impl.AliquotaGatewayImpl;
import br.com.itau.geradornotafiscal.model.*;
import br.com.itau.geradornotafiscal.service.CalcularAliquotaFactory;
import br.com.itau.geradornotafiscal.service.impl.AliquotaServiceImpl;
import br.com.itau.geradornotafiscal.service.impl.CalcularAliquotaPessoaFisicaServiceImpl;
import br.com.itau.geradornotafiscal.service.impl.CalcularAliquotaPessoaJuridicaServiceImpl;
import br.com.itau.geradornotafiscal.service.impl.ItemNotaFiscalServiceImpl;
import br.com.itau.mock.PedidoCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.math.BigDecimal;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ItemNotaFiscalServiceImplTest {

    @InjectMocks
    private ItemNotaFiscalServiceImpl itemNotaFiscalService;

    @Mock
    private CalcularAliquotaFactory calcularAliquotaFactory;


    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

    }


    @Test
    public void deveCalcularTributoPessoaFisicaAcima500() {
        when(calcularAliquotaFactory.getCalcularAliquotaService(any()))
                .thenReturn(new CalcularAliquotaPessoaFisicaServiceImpl(new AliquotaServiceImpl(new AliquotaGatewayImpl())));
        Pedido pedido = PedidoCreator.criarPedido(TipoPessoa.FISICA, Finalidade.ENTREGA, Regiao.SUL);
        List<ItemNotaFiscal> itens = itemNotaFiscalService.gerarItensNotaFiscal(pedido);
        BigDecimal valorTributo = pedido.getItens().get(0).getValorUnitario().multiply(BigDecimal.valueOf(0.12));
        assertEquals(valorTributo, itens.get(0).getValorTributoItem());
    }


    @Test
    public void deveCalcularTributoPessoaFisicaAcima2001() {
        when(calcularAliquotaFactory.getCalcularAliquotaService(any()))
                .thenReturn(new CalcularAliquotaPessoaFisicaServiceImpl(new AliquotaServiceImpl(new AliquotaGatewayImpl())));
        Pedido pedido = PedidoCreator.criarPedido(TipoPessoa.FISICA, Finalidade.ENTREGA, Regiao.SUL);
        pedido.setValorTotalItens(new BigDecimal(2500));
        List<ItemNotaFiscal> itens = itemNotaFiscalService.gerarItensNotaFiscal(pedido);
        BigDecimal valorTributo = pedido.getItens().get(0).getValorUnitario().multiply(BigDecimal.valueOf(0.15));
        assertEquals(valorTributo, itens.get(0).getValorTributoItem());
    }

    @Test
    public void deveCalcularTributoPessoaFisicaAcima3501() {
        when(calcularAliquotaFactory.getCalcularAliquotaService(any()))
                .thenReturn(new CalcularAliquotaPessoaFisicaServiceImpl(new AliquotaServiceImpl(new AliquotaGatewayImpl())));
        Pedido pedido = PedidoCreator.criarPedido(TipoPessoa.FISICA, Finalidade.ENTREGA, Regiao.SUL);
        pedido.setValorTotalItens(new BigDecimal(3600));
        List<ItemNotaFiscal> itens = itemNotaFiscalService.gerarItensNotaFiscal(pedido);
        BigDecimal valorTributo = pedido.getItens().get(0).getValorUnitario().multiply(BigDecimal.valueOf(0.17));
        assertEquals(valorTributo, itens.get(0).getValorTributoItem());
    }


    @Test
    public void deveCalcularTributoPessoaJuridicaSimplesNacionalAcima0() {
        when(calcularAliquotaFactory.getCalcularAliquotaService(any()))
                .thenReturn(new CalcularAliquotaPessoaJuridicaServiceImpl(new AliquotaServiceImpl(new AliquotaGatewayImpl())));

        Pedido pedido = PedidoCreator.criarPedido(TipoPessoa.FISICA, Finalidade.ENTREGA, Regiao.SUL);
        pedido.setValorTotalItens(new BigDecimal(500));
        pedido.getDestinatario().setRegimeTributacao(RegimeTributacaoPJ.SIMPLES_NACIONAL);
        List<ItemNotaFiscal> itens = itemNotaFiscalService.gerarItensNotaFiscal(pedido);
        BigDecimal valorTributo = pedido.getItens().get(0).getValorUnitario().multiply(BigDecimal.valueOf(0.03));
        assertEquals(valorTributo, itens.get(0).getValorTributoItem());
    }

    @Test
    public void deveCalcularTributoPessoaJuridicaSimplesNacionalAcima1000() {
        when(calcularAliquotaFactory.getCalcularAliquotaService(any()))
                .thenReturn(new CalcularAliquotaPessoaJuridicaServiceImpl(new AliquotaServiceImpl(new AliquotaGatewayImpl())));

        Pedido pedido = PedidoCreator.criarPedido(TipoPessoa.FISICA, Finalidade.ENTREGA, Regiao.SUL);
        pedido.setValorTotalItens(new BigDecimal(1500));
        pedido.getDestinatario().setRegimeTributacao(RegimeTributacaoPJ.SIMPLES_NACIONAL);
        List<ItemNotaFiscal> itens = itemNotaFiscalService.gerarItensNotaFiscal(pedido);
        BigDecimal valorTributo = pedido.getItens().get(0).getValorUnitario().multiply(BigDecimal.valueOf(0.07));
        assertEquals(valorTributo, itens.get(0).getValorTributoItem());
    }


    @Test
    public void deveCalcularTributoPessoaJuridicaLucroRealAcima1000() {
        when(calcularAliquotaFactory.getCalcularAliquotaService(any()))
                .thenReturn(new CalcularAliquotaPessoaJuridicaServiceImpl(new AliquotaServiceImpl(new AliquotaGatewayImpl())));

        Pedido pedido = PedidoCreator.criarPedido(TipoPessoa.FISICA, Finalidade.ENTREGA, Regiao.SUL);
        pedido.setValorTotalItens(new BigDecimal(1500));
        pedido.getDestinatario().setRegimeTributacao(RegimeTributacaoPJ.LUCRO_REAL);
        List<ItemNotaFiscal> itens = itemNotaFiscalService.gerarItensNotaFiscal(pedido);
        BigDecimal valorTributo = pedido.getItens().get(0).getValorUnitario().multiply(BigDecimal.valueOf(0.09));
        assertEquals(valorTributo, itens.get(0).getValorTributoItem());
    }


    @Test
    public void deveCalcularTributoPessoaJuridicaLucroRealAcima2500() {
        when(calcularAliquotaFactory.getCalcularAliquotaService(any()))
                .thenReturn(new CalcularAliquotaPessoaJuridicaServiceImpl(new AliquotaServiceImpl(new AliquotaGatewayImpl())));

        Pedido pedido = PedidoCreator.criarPedido(TipoPessoa.FISICA, Finalidade.ENTREGA, Regiao.SUL);
        pedido.setValorTotalItens(new BigDecimal(2600));
        pedido.getDestinatario().setRegimeTributacao(RegimeTributacaoPJ.LUCRO_REAL);
        List<ItemNotaFiscal> itens = itemNotaFiscalService.gerarItensNotaFiscal(pedido);
        BigDecimal valorTributo = pedido.getItens().get(0).getValorUnitario().multiply(BigDecimal.valueOf(0.15));
        assertEquals(valorTributo, itens.get(0).getValorTributoItem());
    }

    @Test
    public void deveCalcularTributoPessoaJuridicaLucroPresumidoAcima5000() {
        when(calcularAliquotaFactory.getCalcularAliquotaService(any()))
                .thenReturn(new CalcularAliquotaPessoaJuridicaServiceImpl(new AliquotaServiceImpl(new AliquotaGatewayImpl())));

        Pedido pedido = PedidoCreator.criarPedido(TipoPessoa.FISICA, Finalidade.ENTREGA, Regiao.SUL);
        pedido.setValorTotalItens(new BigDecimal(90000));
        pedido.getDestinatario().setRegimeTributacao(RegimeTributacaoPJ.LUCRO_REAL);
        List<ItemNotaFiscal> itens = itemNotaFiscalService.gerarItensNotaFiscal(pedido);
        BigDecimal valorTributo = pedido.getItens().get(0).getValorUnitario().multiply(BigDecimal.valueOf(0.20));
        assertEquals(valorTributo, itens.get(0).getValorTributoItem());
    }


}
