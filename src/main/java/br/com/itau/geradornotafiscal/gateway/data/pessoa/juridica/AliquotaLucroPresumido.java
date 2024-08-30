package br.com.itau.geradornotafiscal.gateway.data.pessoa.juridica;

import br.com.itau.geradornotafiscal.gateway.data.MontarAliquota;
import br.com.itau.geradornotafiscal.gateway.data.pessoa.Aliquota;
import br.com.itau.geradornotafiscal.model.RegimeTributacaoPJ;

import java.util.List;


public class AliquotaLucroPresumido implements AliquotaPessoaJuridica {

    @Override
    public RegimeTributacaoPJ getRegimeTributacao() {
        return RegimeTributacaoPJ.LUCRO_PRESUMIDO;
    }

    public List<Aliquota> getAliquotas() {
        return MontarAliquota.montar().
                add(0, 999, 0.03).
                add(1000, 2000, 0.09).
                add(2001, 5000, 0.16).
                add(5001, Double.MAX_VALUE, 0.20).getAliquotas();
    }


}
