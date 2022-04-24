package com.example.projeto_rp_condominio_dev.controller;

import com.example.projeto_rp_condominio_dev.dto.HabitanteDTO;
import com.example.projeto_rp_condominio_dev.dto.HabitanteListDTO;
import com.example.projeto_rp_condominio_dev.model.RelatorioFinanceiro;
import com.example.projeto_rp_condominio_dev.service.HabitanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

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

    @GetMapping
    public ResponseEntity <List<HabitanteListDTO>> get(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) Integer mes
    ) {
        List<HabitanteListDTO> habitanteListDTOS = habitanteService.listar(nome, mes);
        if (habitanteListDTOS.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(habitanteListDTOS);
    }

    @GetMapping("/idade/{idade}")
    public ResponseEntity <List<HabitanteDTO>> getByAge(
            @PathVariable Integer idade
    ) {
        List<HabitanteDTO> habitanteDTOS = habitanteService.listarPorIdade(idade);
        if (habitanteDTOS.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(habitanteDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity <HabitanteDTO> getById(
            @PathVariable Long id
    ) {
        HabitanteDTO habitanteDTO = habitanteService.listarPorId(id);
        return ResponseEntity.ok(habitanteDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <Void> delete(
            @PathVariable Long id
    ) {
        habitanteService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/relatorio")
    public ResponseEntity <RelatorioFinanceiro> getRelatorio(){

        RelatorioFinanceiro relatorioFinanceiro = habitanteService.gerarRelatorio();
        return ResponseEntity.ok(relatorioFinanceiro);
    }
}
