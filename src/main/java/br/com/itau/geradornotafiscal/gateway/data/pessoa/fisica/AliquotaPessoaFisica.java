package br.com.itau.geradornotafiscal.gateway.data.pessoa.fisica;


import br.com.itau.geradornotafiscal.gateway.data.MontarAliquota;
import br.com.itau.geradornotafiscal.gateway.data.pessoa.Aliquota;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AliquotaPessoaFisica {

    public static List<Aliquota> getAliquotas() {
        return MontarAliquota.montar().
                add(0, 499, 0).
                add(500, 2000, 0.12).
                add(2001, 3500, 0.15).
                add(3501, Double.MAX_VALUE, 0.17).getAliquotas();
    }

}
