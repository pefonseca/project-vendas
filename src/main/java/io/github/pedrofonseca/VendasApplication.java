package io.github.pedrofonseca;

import io.github.pedrofonseca.domain.entity.Cliente;
import io.github.pedrofonseca.domain.repository.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class VendasApplication {

    @Bean
    public CommandLineRunner init(@Autowired Clientes clientes) {
        return args -> {
            clientes.save(new Cliente(null,"Pedro"));
            clientes.save(new Cliente(null,"Bento"));

            List<Cliente> clienteList = clientes.findAll();
            clienteList.forEach(System.out::println);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
