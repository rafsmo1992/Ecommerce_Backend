package org.ecommerce.controller;

import lombok.RequiredArgsConstructor;
import org.ecommerce.domain.Product;
import org.ecommerce.domain.dto.ProductDto;
import org.ecommerce.mapper.ProductMapper;
import org.ecommerce.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ecommercee/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping
    public ResponseEntity<List<ProductDto>> getProducts() {
        List<Product> productList = productService.getProducts();
        return ResponseEntity.ok(productMapper.mapToProductDtoList(productList));
    }

    @GetMapping(value = "{productId}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long productId) {
        return new ResponseEntity<>(productMapper.mapToProductDto(productService.getProduct(productId)), HttpStatus.OK);
    }

    @DeleteMapping(value = "{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.ok().build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto) {
        Product product = productMapper.mapToProduct(productDto);
        Product savedProduct = productService.saveProduct(product);
        return ResponseEntity.ok(productMapper.mapToProductDto(savedProduct));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createProduct(@RequestBody ProductDto productDto)  {
        Product product = productMapper.mapToProduct(productDto);
        Product savedProduct = productService.saveProduct(product);
        return ResponseEntity.ok().build();
    }

}

