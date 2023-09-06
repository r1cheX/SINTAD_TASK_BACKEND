package com.sintad.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private String model;

    public NotFoundException(String model) {
        super(String.format("%s no fue encontrado", model));
        this.model = model;
    }
}
