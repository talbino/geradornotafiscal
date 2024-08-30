package br.com.itau.geradornotafiscal.gateway.data.pessoa.juridica;

import br.com.itau.geradornotafiscal.gateway.data.MontarAliquota;
import br.com.itau.geradornotafiscal.gateway.data.pessoa.Aliquota;
import br.com.itau.geradornotafiscal.model.RegimeTributacaoPJ;

import java.util.List;


public class AliquotaSimplesNacional implements AliquotaPessoaJuridica {

    @Override
    public RegimeTributacaoPJ getRegimeTributacao() {
        return RegimeTributacaoPJ.SIMPLES_NACIONAL;
    }

    public List<Aliquota> getAliquotas() {
        return MontarAliquota.montar()
                .add(0, 999, 0.03)
                .add(1000, 2000, 0.07)
                .add(2001, 5000, 0.13)
                .add(5001, Double.MAX_VALUE, 0.19)
                .getAliquotas();
    }


}
