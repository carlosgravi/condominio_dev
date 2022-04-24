package com.example.projeto_rp_condominio_dev.repository;

import com.example.projeto_rp_condominio_dev.model.Habitante;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class SpecificationsHabitante {

    public static Specification<Habitante> nome(String nome){
        return (root, query, criteriaBuilder) -> {
            if(nome == null) {
                return criteriaBuilder.like(root.get("nome"), "%%");
            } else return criteriaBuilder.like(root.get("nome"), "%" + nome + "%");
        };
    }

    public static Specification<Habitante> idade(LocalDate dtMax) {
        return (root, query, criteriaBuilder) -> {
            if(dtMax == null) {
                return criteriaBuilder.equal(criteriaBuilder.literal(1), 1);
            } else return criteriaBuilder.lessThanOrEqualTo(root.get("dtNasc"), dtMax);
        };
    }
}
