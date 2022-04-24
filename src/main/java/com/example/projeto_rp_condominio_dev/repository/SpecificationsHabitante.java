package com.example.projeto_rp_condominio_dev.repository;

import com.example.projeto_rp_condominio_dev.model.Habitante;
import org.springframework.data.jpa.domain.Specification;

public class SpecificationsHabitante {

    public static Specification<Habitante> nome(String nome){
        return (root, query, criteriaBuilder) -> {
            if(nome == null) {
                return criteriaBuilder.like(root.get("nome"), "%%");
            } else return criteriaBuilder.like(root.get("nome"), "%" + nome + "%");
        };
    }
}
