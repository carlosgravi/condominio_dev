package com.example.projeto_rp_condominio_dev.service.exceptions;


public class RequiredFieldMissingException extends RuntimeException{

    public RequiredFieldMissingException(String message) {

        super(message);
    }
}
