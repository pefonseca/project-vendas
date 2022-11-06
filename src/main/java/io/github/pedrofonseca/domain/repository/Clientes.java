package io.github.pedrofonseca.domain.repository;

import io.github.pedrofonseca.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Clientes extends JpaRepository<Cliente, Integer> {

    @Query(" select c from Cliente c where c.nome like :nome ")
    List<Cliente> encontrarPorNome(String nome);
    boolean existsByNome(String nome);

    @Query(" select c from Cliente c left join fetch c.pedidos where c.id = :id ")
    Cliente findClienteFetchPedidos(Integer id);
}
