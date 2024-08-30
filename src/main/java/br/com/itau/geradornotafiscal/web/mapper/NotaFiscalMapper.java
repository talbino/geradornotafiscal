package br.com.itau.geradornotafiscal.web.mapper;


import br.com.itau.geradornotafiscal.model.NotaFiscal;
import br.com.itau.geradornotafiscal.web.data.response.NotaFiscalResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface NotaFiscalMapper {

    NotaFiscalResponse notaFiscalToNotaFiscalResponse(NotaFiscal notaFiscal);
}
