package com.palibra.walletapi.controller.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ApiResponse {
    private boolean success;
    private String message;

    private ApiResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static ResponseEntity<?> Fail(String message, HttpStatus status) {
        return new ResponseEntity<>(new ApiResponse(false, message), status);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static ResponseEntity<?> Fail(String message, HttpStatus status, Exception e) {
        return new ResponseEntity<>(new ApiResponse(false, message +"(" + e.getMessage() + ")"), status);
    }

    public static ResponseEntity<?> Success(Object contents) {
        return ResponseEntity.ok().body(contents);
    }

    public static ResponseEntity<?> Success(String message) {
        return new ResponseEntity<>(new ApiResponse(true, message), HttpStatus.OK);
    }
}
