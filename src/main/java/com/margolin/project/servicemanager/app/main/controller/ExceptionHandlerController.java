package com.margolin.project.servicemanager.app.main.controller;

import com.margolin.project.servicemanager.app.main.exceptions.GeneralException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {GeneralException.class})
    protected ResponseEntity<Object> handleConflict(
            GeneralException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getErrorMessage(),
                new HttpHeaders(), ex.getHttpStatus(), request);
    }
}
