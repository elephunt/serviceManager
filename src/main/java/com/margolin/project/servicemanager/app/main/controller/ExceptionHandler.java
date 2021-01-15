package com.margolin.project.servicemanager.app.main.controller;

import com.margolin.project.servicemanager.app.main.exceptions.PatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandler  extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {PatchException.class})
    protected ResponseEntity<Object> handleConflict(
            PatchException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getPatchError(),
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
