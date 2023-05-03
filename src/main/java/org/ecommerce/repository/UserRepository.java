package org.ecommerce.repository;

import org.ecommerce.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends CrudRepository<User, Long> {

    @Override
    List<User> findAll();
    Optional<User> findByUserEmail(String email);

    List<User> findByUserEmailContaining(String partialEmail);
}
