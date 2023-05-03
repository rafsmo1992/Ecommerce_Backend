package org.ecommerce.api.repository;

import org.ecommerce.api.domain.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {

    @Override
    List<Currency> findAll();
}
