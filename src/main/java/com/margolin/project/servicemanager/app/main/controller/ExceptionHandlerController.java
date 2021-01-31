package com.margolin.project.servicemanager.app.main.controller;

import com.margolin.project.servicemanager.app.main.exceptions.ErrorResponse;
import com.margolin.project.servicemanager.app.main.exceptions.GeneralException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.*;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {GeneralException.class})
    protected ResponseEntity<Object> handleConflict(
            GeneralException ex, WebRequest request) {
        ErrorResponse error = new ErrorResponse("Server Error", new LinkedList<>(),null);
        return handleExceptionInternal(ex, error,
                new HttpHeaders(), ex.getHttpStatus(), request);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("Server Error", details,null);
        return handleExceptionInternal(ex, error,
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
    @ExceptionHandler(value = {ConstraintViolationException.class})
    protected ResponseEntity<Object> handle(ConstraintViolationException exception , WebRequest webRequest){
        List<String> validationsError = exception.getConstraintViolations().stream().map(ConstraintViolation::getMessageTemplate).collect(Collectors.toList());
        ErrorResponse error = new ErrorResponse("Input error", validationsError,null);
        return handleExceptionInternal(exception, error,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, webRequest);

    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        final List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        Map<String, Set<String>> errorsMap =  fieldErrors.stream().collect(
                Collectors.groupingBy(FieldError::getField,
                        Collectors.mapping(FieldError::getDefaultMessage, Collectors.toSet())
                )
        );
        ErrorResponse error = new ErrorResponse("Input error", new LinkedList<>(),errorsMap);
        return handleExceptionInternal(ex, error,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }


}
