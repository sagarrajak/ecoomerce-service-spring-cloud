package com.fancystore.productservice.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundError extends RuntimeException {
    public ProductNotFoundError(String message) {
        super(message);
    }
}
