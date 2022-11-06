package io.github.pedrofonseca.domain.repository;

import io.github.pedrofonseca.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Clientes extends JpaRepository<Cliente, Integer> {

    List<Cliente> findByNomeLike(String nome);
    List<Cliente> findByNomeOrIdOrderById(String nome, Integer id);
    boolean existsByNome(String nome);
}
