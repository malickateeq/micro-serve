package com.malikatique.microserve.exception;

import lombok.Data;

@Data
public class ValidationException extends RuntimeException {
    private String message;
    private String field;
    private int code = 422;

    public ValidationException(String message) {
        super(message);
        this.message = message;
    }

    public ValidationException(String message, String field) {
        super(message);
        this.message = message;
        this.field = field;
    }

    public ValidationException(String message, String field, int code) {
        super(message);
        this.message = message;
        this.code = code;
        this.field = field;
    }
}
