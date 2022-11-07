package io.github.pedrofonseca.rest.controller;

import io.github.pedrofonseca.exception.RegraNegocioException;
import io.github.pedrofonseca.rest.ApiErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(RegraNegocioException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleRegraNegocioException(RegraNegocioException ex) {
        String messageError = ex.getMessage();
        return new ApiErrors(messageError);
    }
}
