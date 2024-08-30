package br.com.itau.geradornotafiscal.gateway.data.pessoa;



import br.com.itau.geradornotafiscal.gateway.data.pessoa.juridica.AliquotaLucroPresumido;
import br.com.itau.geradornotafiscal.gateway.data.pessoa.juridica.AliquotaLucroReal;
import br.com.itau.geradornotafiscal.gateway.data.pessoa.juridica.AliquotaPessoaJuridica;
import br.com.itau.geradornotafiscal.gateway.data.pessoa.juridica.AliquotaSimplesNacional;
import br.com.itau.geradornotafiscal.model.RegimeTributacaoPJ;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public enum AliquotaPessoaJuridicaStrategy {

    SIMPLES_NACIONAL(new AliquotaSimplesNacional()),
    LUCRO_REAL(new AliquotaLucroReal()),
    LUCRO_PRESUMIDO(new AliquotaLucroPresumido());

    private final AliquotaPessoaJuridica aliquotaPessoaJuridica;

    public static Optional<AliquotaPessoaJuridica> getAliquotaPessoaJuridica(RegimeTributacaoPJ regimeTributacaoPJ){
        return Arrays.stream(values())
                .filter( v -> v.aliquotaPessoaJuridica.getRegimeTributacao().equals(regimeTributacaoPJ))
                .map(v -> v.aliquotaPessoaJuridica).findFirst();
    }

    public List<Aliquota> getAliquotas() {
        return this.aliquotaPessoaJuridica.getAliquotas();
    }


    public RegimeTributacaoPJ getRegimeTributacaoPJ(){
        return this.aliquotaPessoaJuridica.getRegimeTributacao();
    }

}
