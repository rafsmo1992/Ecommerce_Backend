package org.ecommerce.service;

import lombok.RequiredArgsConstructor;
import org.ecommerce.domain.Product;
import org.ecommerce.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product getProduct(Long id) {

        return productRepository.findById(id).orElse(null);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Product saveProduct(final Product product) {
        return productRepository.save(product);
    }
}