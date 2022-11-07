package io.github.pedrofonseca.service;

import io.github.pedrofonseca.domain.entity.Pedido;
import io.github.pedrofonseca.rest.dto.PedidoDTO;

public interface PedidoService {
    Pedido salvar(PedidoDTO dto);
}
