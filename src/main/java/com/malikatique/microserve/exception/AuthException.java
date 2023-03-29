package com.malikatique.microserve.exception;

import lombok.Data;

@Data
public class AuthException extends RuntimeException {

    private String message;
    private String field;
    private int code = 401;

    public AuthException(String message) {
        super(message);
        this.message = message;
    }

    public AuthException(String message, String field) {
        super(message);
        this.message = message;
        this.field = field;
    }

    public AuthException(String message, String field, int code) {
        super(message);
        this.message = message;
        this.code = code;
        this.field = field;
    }
}
