package io.github.pedrofonseca.domain.repository;

import io.github.pedrofonseca.domain.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemsPedido extends JpaRepository<ItemPedido, Integer> {
}
