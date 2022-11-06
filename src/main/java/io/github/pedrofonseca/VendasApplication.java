package io.github.pedrofonseca;

import io.github.pedrofonseca.domain.entity.Cliente;
import io.github.pedrofonseca.domain.entity.Pedido;
import io.github.pedrofonseca.domain.repository.Clientes;
import io.github.pedrofonseca.domain.repository.Pedidos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class VendasApplication {

    @Bean
    public CommandLineRunner init(
            @Autowired Clientes clientes,
            @Autowired Pedidos pedidos
    ) {
        return args -> {
            System.out.println("\nSalvando Clientes.");
            Cliente cliente = new Cliente(null,"Fulano");
            clientes.save(cliente);

            Pedido p = new Pedido();
            p.setCliente(cliente);
            p.setDataPedido(LocalDate.now());
            p.setTotal(BigDecimal.valueOf(100));

            pedidos.save(p);

//            Cliente result = clientes.findClienteFetchPedidos(cliente.getId());
//            System.out.println(result);
//            System.out.println(result.getPedidos());

            pedidos.findByCliente(cliente).forEach(System.out::println);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
