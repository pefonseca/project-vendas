package io.github.pedrofonseca.rest;

import lombok.Getter;

import java.util.Collections;
import java.util.List;

public class ApiErrors {

    @Getter
    private List<String> errors;

    public ApiErrors(String messageErro) {
        this.errors = Collections.singletonList(messageErro);
    }
}
