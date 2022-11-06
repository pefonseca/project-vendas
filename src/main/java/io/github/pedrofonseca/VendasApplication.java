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

            List<Cliente> clienteList = clientes.findAll();
            clienteList.forEach(System.out::println);

            System.out.println("\nAtualizando Clientes.");
            clienteList.forEach(c -> {
                c.setNome(c.getNome() + " atualizado.");
                clientes.update(c);
            });

            System.out.println("\nBuscando Clientes Atualizandos.");
            clienteList = clientes.findAll();
            clienteList.forEach(System.out::println);

            System.out.println("\nBuscando Clientes Por Nome.");
            clientes.findByName("Ped").forEach(System.out::println);

            System.out.println("\nDeletando Clientes.");
            clientes.findAll().forEach(c -> {
                clientes.deletar(c);
            });

            clienteList = clientes.findAll();

            if(clienteList.isEmpty()) {
                System.out.println("Nenhum cliente encontrado.");
            } else {
                clienteList.forEach(System.out::println);
            }
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
