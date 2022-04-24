package com.example.projeto_rp_condominio_dev.service;

import com.example.projeto_rp_condominio_dev.dto.HabitanteDTO;
import com.example.projeto_rp_condominio_dev.model.Habitante;
import com.example.projeto_rp_condominio_dev.repository.HabitanteRepository;
import com.example.projeto_rp_condominio_dev.service.exceptions.RequiredFieldMissingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

@Service
public class HabitanteService {

    @Autowired
    private HabitanteRepository habitanteRepository;

    private Long salvar(HabitanteDTO habitanteDTO) {
        Habitante habitante = validarEConverterDTO(habitanteDTO);
        habitante = habitanteRepository.save(habitante);
        return habitante.getId();
    }

    private Habitante validarEConverterDTO(HabitanteDTO habitanteDTO) {
        existsNome(habitanteDTO);
        existsSobrenome(habitanteDTO);
        existsCPF(habitanteDTO);
        existsDtNasc(habitanteDTO);
        existsRenda(habitanteDTO);
        isUniqueCPF(habitanteDTO);
        isValidCPF(habitanteDTO.getCpf());
        LocalDate dtNasc = verificationDtNasc(habitanteDTO);

        Habitante habitante = new Habitante();
        habitante.setNome(habitanteDTO.getNome());
        habitante.setSobrenome(habitanteDTO.getSobrenome());
        habitante.setCpf(habitanteDTO.getCpf());
        habitante.setDtNasc(dtNasc);
        habitante.setRenda(habitanteDTO.getRenda());

        return habitante;
    }

    private void existsNome(HabitanteDTO habitanteDTO) {
        if (habitanteDTO.getNome() == null) {
            throw new RequiredFieldMissingException("O campo Nome é obrigatório");
        }
    }

    private void existsSobrenome(HabitanteDTO habitanteDTO) {
        if (habitanteDTO.getSobrenome() == null) {
            throw new RequiredFieldMissingException("O campo Sobrenome é obrigatório");
        }
    }

    private void existsCPF(HabitanteDTO habitanteDTO) {
        if (habitanteDTO.getCpf() == null) {
            throw new RequiredFieldMissingException("O campo CPF é obrigatório");
        }
    }

    private void existsDtNasc(HabitanteDTO habitanteDTO) {
        if (habitanteDTO.getDtNasc() == null) {
            throw new RequiredFieldMissingException("O campo Data de Nascimento é obrigatório");
        }
    }

    private void existsRenda(HabitanteDTO habitanteDTO) {
        if (habitanteDTO.getRenda() == null) {
            throw new RequiredFieldMissingException("O campo Renda é obrigatório");
        }
    }

    private void isUniqueCPF(HabitanteDTO habitanteDTO) {
        Optional<Habitante> optionalHabitante = habitanteRepository.findFirstByCpf(habitanteDTO.getCpf());
        if (optionalHabitante.isPresent()) {
            throw new EntityExistsException("Já existe um habitante cadastrado com este CPF: " + habitanteDTO.getCpf());
        }
    }

    public static boolean isValidCPF(String CPF) {
        // considera-se erro CPF's formados por uma sequencia de numeros iguais
        if (CPF.equals("00000000000") ||
                CPF.equals("11111111111") ||
                CPF.equals("22222222222") || CPF.equals("33333333333") ||
                CPF.equals("44444444444") || CPF.equals("55555555555") ||
                CPF.equals("66666666666") || CPF.equals("77777777777") ||
                CPF.equals("88888888888") || CPF.equals("99999999999") ||
                (CPF.length() != 11))
            return(false);

        char dig10, dig11;
        int sm, i, r, num, peso;

        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
        try {
            // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 10;
            for (i=0; i<9; i++) {
                // converte o i-esimo caractere do CPF em um numero:
                // por exemplo, transforma o caractere '0' no inteiro 0
                // (48 eh a posicao de '0' na tabela ASCII)
                num = (int)(CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else dig10 = (char)(r + 48); // converte no respectivo caractere numerico

            // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;
            for(i=0; i<10; i++) {
                num = (int)(CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig11 = '0';
            else dig11 = (char)(r + 48);

            // Verifica se os digitos calculados conferem com os digitos informados.
            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
                return(true);
            else return(false);
        } catch (IllegalArgumentException erro) {
            return(false);
        }
    }

    private LocalDate verificationDtNasc(HabitanteDTO habitanteDTO) throws DateTimeParseException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dtNasc = LocalDate.parse(habitanteDTO.getDtNasc(), dateTimeFormatter);

        return dtNasc;
    }
}