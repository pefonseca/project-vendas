package io.github.pedrofonseca.service.impl;

import io.github.pedrofonseca.domain.entity.Cliente;
import io.github.pedrofonseca.domain.entity.ItemPedido;
import io.github.pedrofonseca.domain.entity.Pedido;
import io.github.pedrofonseca.domain.entity.Produto;
import io.github.pedrofonseca.domain.repository.Clientes;
import io.github.pedrofonseca.domain.repository.ItemsPedido;
import io.github.pedrofonseca.domain.repository.Pedidos;
import io.github.pedrofonseca.domain.repository.Produtos;
import io.github.pedrofonseca.exception.RegraNegocioException;
import io.github.pedrofonseca.rest.dto.ItemPedidoDTO;
import io.github.pedrofonseca.rest.dto.PedidoDTO;
import io.github.pedrofonseca.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final Pedidos repository;
    private final Clientes clientesRepository;
    private final Produtos produtosRepository;
    private final ItemsPedido itemsPedidoRepository;

    @Override
    @Transactional
    public Pedido salvar(PedidoDTO dto) {
        Integer idCliente = dto.getCliente();
        Cliente cliente = clientesRepository
                .findById(idCliente)
                .orElseThrow(() ->
                        new RegraNegocioException("Código de cliente inválido."));

        Pedido pedido = Pedido.builder()
                                .total(dto.getTotal())
                                .dataPedido(LocalDate.now())
                                .cliente(cliente)
                                .build();

        List<ItemPedido> pedidoList = converterItems(pedido, dto.getItems());
        repository.save(pedido);
        itemsPedidoRepository.saveAll(pedidoList);
        pedido.setItens(pedidoList);

        return pedido;
    }

    private List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> items) {
        if(items.isEmpty()) {
            throw new RegraNegocioException("Não é possível realizar um pedido sem items.");
        }

        return items
                .stream()
                .map( dto -> {
                    Integer idProduto = dto.getProduto();
                    Produto produto = produtosRepository
                            .findById(idProduto)
                            .orElseThrow(
                                    () -> new RegraNegocioException("Código de cliente inválido: " + idProduto));

                    return ItemPedido.builder()
                            .quantidade(dto.getQuantidade())
                            .pedido(pedido)
                            .produto(produto)
                            .build();

                }).collect(Collectors.toList());
    }
}
