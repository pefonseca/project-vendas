package io.github.pedrofonseca.domain.entity;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    private Integer id;
    private String nome;
}
