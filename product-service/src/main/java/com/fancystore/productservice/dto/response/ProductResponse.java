package com.fancystore.productservice.dto.response;

import com.fancystore.productservice.ResponseType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse<T> {
    private T data;
    private String message;
    private ResponseType responseType;
}
