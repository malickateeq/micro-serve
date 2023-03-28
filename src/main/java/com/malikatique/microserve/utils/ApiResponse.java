package com.malikatique.microserve.utils;

import lombok.Data;
//import org.springframework.http.HttpStatusCode;
//import org.springframework.http.ResponseEntity;

@Data
public class ApiResponse {

    private int code;
    private String message;
    private Object data;

    public ApiResponse(Object data, String message, int code) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ApiResponse(Object data, String message) {
        this.message = message;
        this.data = data;
        this.code = 200;
    }

    public ApiResponse(Object data) {
        this.code = 200;
        this.message = "SUCCESS";
        this.data = data;
    }

    public ApiResponse() {
        this.code = 200;
        this.message = "SUCCESS";
        this.data = null;
    }

//    public static ResponseEntity<Object> build(ApiResponse response) {
//        return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getCode()));
//    }
}
