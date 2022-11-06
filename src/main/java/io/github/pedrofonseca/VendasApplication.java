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
            System.out.println("\nSalvando Clientes.");
            clientes.save(new Cliente(null,"Pedro"));
            clientes.save(new Cliente(null,"Bento"));

            boolean existe = clientes.existsByNome("eae");
            System.out.println("Existe um cliente com o nome Pedro? " + existe);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
