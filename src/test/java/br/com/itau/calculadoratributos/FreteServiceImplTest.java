package br.com.itau.calculadoratributos;

import br.com.itau.geradornotafiscal.gateway.data.frete.FreteRegiaoEnum;
import br.com.itau.geradornotafiscal.gateway.impl.FreteGatewayImpl;
import br.com.itau.geradornotafiscal.model.*;
import br.com.itau.geradornotafiscal.service.impl.*;
import br.com.itau.mock.PedidoCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class FreteServiceImplTest {

    @InjectMocks
    private FreteServiceImpl freteService;


    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        freteService = new FreteServiceImpl(new FreteGatewayImpl());
    }



    @Test
    @DisplayName("calcular frete para todas as regiões")
    public void deveCalcularFrete() {
      testarFrete(Regiao.SUL);
      testarFrete(Regiao.SUDESTE);
      testarFrete(Regiao.NORDESTE);
      testarFrete(Regiao.NORTE);
      testarFrete(Regiao.CENTRO_OESTE);
    }


    public void testarFrete(Regiao regiao) {
        Pedido pedido = PedidoCreator.criarPedido(TipoPessoa.FISICA, Finalidade.ENTREGA, regiao);
        BigDecimal valorFrete = freteService.calcularFrete(pedido);
        valorFrete = valorFrete.setScale(2, RoundingMode.HALF_UP);

        BigDecimal nValorFrete =
                pedido.getValorFrete()
                        .multiply(BigDecimal.valueOf(FreteRegiaoEnum.valueOf(regiao.name()).getPercentualFrete()))
                        .setScale(2, RoundingMode.HALF_UP);

        assertEquals(valorFrete, nValorFrete);
    }

}
