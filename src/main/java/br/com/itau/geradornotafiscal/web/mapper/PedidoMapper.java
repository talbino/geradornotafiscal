package br.com.itau.geradornotafiscal.web.mapper;

import br.com.itau.geradornotafiscal.model.Pedido;
import br.com.itau.geradornotafiscal.web.data.request.PedidoRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface PedidoMapper {

    Pedido pedidoRequestToPedido(PedidoRequest pedidoRequest);
}
