package com.example.projeto_rp_condominio_dev.controller;

import com.example.projeto_rp_condominio_dev.dto.HabitanteDTO;
import com.example.projeto_rp_condominio_dev.service.HabitanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/habitante")
public class HabitanteController {

    @Autowired
    private HabitanteService habitanteService;

    @PostMapping
    public ResponseEntity <Long> post(
            @RequestBody HabitanteDTO habitanteDTO
            ) {
        Long idHabitante = habitanteService.salvar(habitanteDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(idHabitante).toUri();

        return ResponseEntity.created(location).body(idHabitante);
    }



}
