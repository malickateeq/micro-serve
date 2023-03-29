package com.malikatique.microserve.exception;

import com.malikatique.microserve.utils.ApiResponse;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity handleConstraintViolationException(ConstraintViolationException e) {
        Map<String, Object> data = new HashMap<>();
        Map<String, Object> errors = new HashMap<>();
        e.getConstraintViolations().forEach(violation -> {
            errors.put(violation.getPropertyPath().toString(), violation.getMessage());
        });
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(422);
        apiResponse.setMessage("Bad Request!");
        data.put("errors", errors);
        apiResponse.setData(data);
        return ApiResponse.build(apiResponse);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity handleValidationException(ValidationException e) {
        Map<String, Object> data = new HashMap<>();
        Map<String, Object> errors = new HashMap<>();
        errors.put(e.getField(), e.getMessage());
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(422);
        apiResponse.setMessage(e.getMessage());
        data.put("errors", errors);
        apiResponse.setData(data);
        return ApiResponse.build(apiResponse);
    }

    @ExceptionHandler(AuthException.class)
    public ResponseEntity handleValidationException(AuthException e) {
        Map<String, Object> data = new HashMap<>();
        Map<String, Object> errors = new HashMap<>();
        errors.put("reason", e.getMessage());
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(401);
        apiResponse.setMessage(e.getMessage());
        data.put("errors", errors);
        apiResponse.setData(data);
        return ApiResponse.build(apiResponse);
    }

    @ExceptionHandler(value = {ExpiredJwtException.class})
    public ResponseEntity handleExpiredJwtException(ExpiredJwtException e) {
        Map<String, Object> data = new HashMap<>();
        Map<String, Object> errors = new HashMap<>();
        errors.put("reason", e.getMessage());
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(401);
        apiResponse.setMessage(e.getMessage());
        data.put("errors", errors);
        apiResponse.setData(data);
        return ApiResponse.build(apiResponse);
    }
}
