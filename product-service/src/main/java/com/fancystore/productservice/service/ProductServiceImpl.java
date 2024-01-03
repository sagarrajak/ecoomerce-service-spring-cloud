package com.fancystore.productservice.service;

import com.fancystore.productservice.dto.request.ProductCreateRequestDto;
import com.fancystore.productservice.model.Product;
import com.fancystore.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product createProduct(ProductCreateRequestDto productCreateRequestDto) {
        Product product = productCreateRequestDto.getproduct();
        return  productRepository.save(product);
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findByProductId(String id) {
        return this.productRepository.findById(id);
    }
}
