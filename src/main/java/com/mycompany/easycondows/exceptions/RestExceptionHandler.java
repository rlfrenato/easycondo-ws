package com.mycompany.easycondows.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = "Malformed JSON request";
        return buildResponseEntity(new RestApiError(HttpStatus.BAD_REQUEST, error, ex));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex) {
        String error = "Constraint Violation";

        List<RestApiSubError> subErrors = new ArrayList<RestApiSubError>();
        Iterator<ConstraintViolation<?>> it = ex.getConstraintViolations().iterator();
        while (it.hasNext()) {
            ConstraintViolation violation = it.next();
            RestApiSubError subError = new RestApiSubError();
            subError.setResource(violation.getRootBeanClass().getSimpleName());
            subError.setRejectedValue(violation.getInvalidValue());
            subError.setMessage(violation.getMessage());
            subError.setField(violation.getPropertyPath().toString());

            subErrors.add(subError);
        }


        return buildResponseEntity(new RestApiError(HttpStatus.BAD_REQUEST, error, subErrors, ex));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<Object> handleUniqueConstraintViolation(DataIntegrityViolationException ex) {
        String error = "Unique Constraint Violation";
        return buildResponseEntity(new RestApiError(HttpStatus.CONFLICT, error, ex));
    }

    private ResponseEntity<Object> buildResponseEntity(RestApiError restApiError) {
        return new ResponseEntity<>(restApiError, restApiError.getStatus());
    }

}
