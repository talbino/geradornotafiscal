package br.com.itau.geradornotafiscal.gateway.data.pessoa.juridica;

import br.com.itau.geradornotafiscal.gateway.data.pessoa.Aliquota;
import br.com.itau.geradornotafiscal.model.RegimeTributacaoPJ;

import java.util.List;

public interface AliquotaPessoaJuridica {

    RegimeTributacaoPJ getRegimeTributacao();
    List<Aliquota> getAliquotas();

}
