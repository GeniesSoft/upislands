package com.geniessoft.backend.utility.customvalidator;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Optional;

@Data
public class Response<T> {

    Optional<T> optionalT;
    String message;
    HttpStatus status;

    public Response(Optional<T> optionalT, String message) {
        this.optionalT = optionalT;
        this.message = message;
    }

    public Response(Optional<T> optionalT, String message, HttpStatus status) {
        this.optionalT = optionalT;
        this.message = message;
        this.status = status;
    }

    public Response(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }
}
