package com.example.electronic_store.repositories;

import com.example.electronic_store.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,String> {
 Optional<User> findByEmail(String email);
    Optional<User> findByEmailAndPassword(String email,String password);

    Optional<List<User>> findByNameContaining(String Keywords);
}
