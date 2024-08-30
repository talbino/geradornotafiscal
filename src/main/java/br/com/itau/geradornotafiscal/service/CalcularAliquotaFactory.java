package br.com.itau.geradornotafiscal.service;

import br.com.itau.geradornotafiscal.model.Pedido;
import br.com.itau.geradornotafiscal.model.TipoPessoa;
import br.com.itau.geradornotafiscal.service.impl.CalcularAliquotaPessoaFisicaServiceImpl;
import br.com.itau.geradornotafiscal.service.impl.CalcularAliquotaPessoaJuridicaServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class CalcularAliquotaFactory {


    private final ApplicationContext applicationContext;

    private Map<TipoPessoa, Class<? extends CalcularAliquotaService>> getMapAliquotaFactory(){
       final HashMap<TipoPessoa, Class<? extends CalcularAliquotaService>>  map = new HashMap<>();
       map.put(TipoPessoa.FISICA, CalcularAliquotaPessoaFisicaServiceImpl.class);
       map.put(TipoPessoa.JURIDICA, CalcularAliquotaPessoaJuridicaServiceImpl.class);
       return map;
    }


    public CalcularAliquotaService getCalcularAliquotaService(Pedido pedido){
        return applicationContext.getBean(getMapAliquotaFactory().get(pedido.getDestinatario().getTipoPessoa()));
    }

}
