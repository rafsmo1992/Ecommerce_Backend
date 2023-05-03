package org.ecommerce.repository;

import org.ecommerce.domain.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    @Override
    List<Product> findAll();

    @Override
    Product save(Product reader);

    @Override
    Optional<Product> findById(Long id);

    @Override
    void deleteById(Long id);
}
