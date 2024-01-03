package com.fancystore.productservice.service;

import com.fancystore.productservice.dto.request.ProductCreateRequestDto;
import com.fancystore.productservice.model.Product;

import java.util.List;
import java.util.Optional;


public interface ProductService {
    public Product createProduct(ProductCreateRequestDto productCreateRequestDto);

    public List<Product> getProducts();

    Optional<Product> findByProductId(String id);
}
