package com.example.projeto_rp_condominio_dev.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "habitantes")
@Data
public class Habitante {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "habitante_gen")
    @SequenceGenerator(name = "habitante_gen", sequenceName = "habitantes_id_seq", allocationSize = 1)
    private Long id;

    private String nome;

    private String sobrenome;

    private String cpf;

    @Column(name = "dt_nasc")
    private LocalDate dtNasc;

    private Double renda;
}
