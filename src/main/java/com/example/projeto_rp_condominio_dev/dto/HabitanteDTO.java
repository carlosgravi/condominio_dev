package com.example.projeto_rp_condominio_dev.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class HabitanteDTO {

    private String nome;

    private String sobrenome;

    private String cpf;

    private String dtNasc;

    private Double renda;
}
