package com.mycompany.easycondows.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

class RestApiError {

    Integer statusCode;
    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String message;
    private String debugMessage;
    private List<RestApiSubError> subErrors;

    private RestApiError() {
        timestamp = LocalDateTime.now();
    }

    RestApiError(HttpStatus status) {
        this();
        this.status = status;
    }

    RestApiError(HttpStatus status, Throwable ex) {
        this();
        this.status = status;
        this.statusCode = status.value();
        this.message = "Unexpected error";
        this.debugMessage = ex.getLocalizedMessage();
    }

    RestApiError(HttpStatus status, String message, Throwable ex) {
        this();
        this.status = status;
        this.statusCode = status.value();
        this.message = message;
        this.debugMessage = ex.getLocalizedMessage();
    }

    RestApiError(HttpStatus status, String message, List<RestApiSubError> subErrors, Throwable ex) {
        this();
        this.status = status;
        this.statusCode = status.value();
        this.message = message;
        this.debugMessage = ex.getLocalizedMessage();
        this.subErrors = subErrors;
    }


    public HttpStatus getStatus() {
        return status;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDebugMessage() {
        return debugMessage;
    }

    public List<RestApiSubError> getSubErrors() {
        return subErrors;
    }
}