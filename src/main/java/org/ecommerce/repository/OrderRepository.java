package org.ecommerce.repository;

import org.ecommerce.domain.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
    @Override
    List<Order> findAll();

    @Override
    Optional<Order> findById(Long id);

    @Override
    void deleteById(Long id);

    @Override
    Order save(Order order);


}
