package com.fancystore.orderservice.error;

import lombok.Getter;

@Getter
public class CustomIllegalArgumentException extends Exception {
    public Object body;
    public CustomIllegalArgumentException(String message) {
        super(message);
    }
    public CustomIllegalArgumentException(String message, Object body) {
        super(message);
        this.body = body;
    }

}
