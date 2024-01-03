package com.fancystore.productservice.controller;

import com.fancystore.productservice.ResponseType;
import com.fancystore.productservice.dto.request.ProductCreateRequestDto;
import com.fancystore.productservice.dto.response.ProductResponse;
import com.fancystore.productservice.error.ProductNotFoundError;
import com.fancystore.productservice.model.Product;
import com.fancystore.productservice.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/v1/product")
public class ProductController {

 @Autowired
 private ProductService productService;

   @PostMapping
   @ResponseStatus(HttpStatus.CREATED)
   public ProductResponse<Product> createProduct(@RequestBody  ProductCreateRequestDto productCreateRequestDto) {
    Product product = this.productService.createProduct(productCreateRequestDto);
    return new ProductResponse<Product>(product, "product added!", ResponseType.success);
   }


   @GetMapping
   public ProductResponse<List<Product>> getProducts() {
    List<Product> products = this.productService.getProducts();
    return new ProductResponse<List<Product>>(products, "", ResponseType.success);
   }


   @GetMapping("{id}")
   public ResponseEntity<ProductResponse<Product>> getProduct(@PathVariable("id") String id) {
       Product product = this.productService.findByProductId(id).orElseThrow(() -> new ProductNotFoundError("Product not found!"));
       return ResponseEntity.ok(new ProductResponse<Product>(product, "", ResponseType.success));
   }

}
