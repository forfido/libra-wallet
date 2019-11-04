package com.palibra.walletapi.controller.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ApiResponse {
    private boolean success;
    private Object contents;

    public ApiResponse(){
    }

    private ApiResponse(boolean success, Object contents) {
        this.success = success;
        this.contents = contents;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getContents() {
        return contents;
    }

    public void setContents(Object contents) {
        this.contents = contents;
    }

    public static ResponseEntity<?> Success(Object contents) {
        return new ResponseEntity<>(new ApiResponse(true, contents), HttpStatus.OK);
    }
}
