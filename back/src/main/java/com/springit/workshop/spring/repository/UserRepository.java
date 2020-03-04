package com.springit.workshop.spring.repository;

import com.springit.workshop.spring.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByEmailAndPassword(String email, String password);
}