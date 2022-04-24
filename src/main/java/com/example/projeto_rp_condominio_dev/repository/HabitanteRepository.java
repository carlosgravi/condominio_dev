package com.example.projeto_rp_condominio_dev.repository;

import com.example.projeto_rp_condominio_dev.model.Habitante;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HabitanteRepository extends CrudRepository <Habitante,Long>, JpaSpecificationExecutor <Habitante> {

    Optional<Habitante> findFirstByCpf(String cpf);
}
