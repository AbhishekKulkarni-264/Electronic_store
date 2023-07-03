package com.example.electronic_store.repositories;

import com.example.electronic_store.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,String> {

}
