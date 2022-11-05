package io.github.pedrofonseca.domain.repository;

import io.github.pedrofonseca.domain.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class Clientes {

    private static final String INSERT = "INSERT INTO CLIENTE (nome) VALUES (?) ";
    private static final String SELECT_ALL = "SELECT * FROM CLIENTE ";
    private static final String UPDATE  = "UPDATE CLIENTE SET nome = ? WHERE id = ? ";
    private static final String DELETE = "DELETE FROM CLIENTE WHERE id = ? ";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Cliente save(Cliente cliente) {
        jdbcTemplate.update(INSERT, cliente.getNome());
        return cliente;
    }

    public Cliente update(Cliente cliente) {
        jdbcTemplate.update(UPDATE, cliente.getNome(), cliente.getId());
        return cliente;
    }

    public void deletar(Cliente cliente) {
        deletar(cliente.getId());
    }

    public void deletar(Integer id) {
        jdbcTemplate.update(DELETE, id);
    }

    public List<Cliente> findByName(String nome) {
        return jdbcTemplate.query(
                SELECT_ALL.concat("WHERE nome LIKE ? "),
                new Object[]{"%" + nome + "%"},
                getRowMapper());
    }

    public List<Cliente> findAll() {
        return jdbcTemplate.query(SELECT_ALL, getRowMapper());
    }

    private static RowMapper<Cliente> getRowMapper() {
        return new RowMapper<Cliente>() {
            @Override
            public Cliente mapRow(ResultSet resultSet, int i) throws SQLException {
                Integer id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                return Cliente.builder().id(id).nome(nome).build();
            }
        };
    }
}
