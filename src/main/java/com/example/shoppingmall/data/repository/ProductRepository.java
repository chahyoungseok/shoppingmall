package com.example.shoppingmall.data.repository;

import com.example.shoppingmall.data.entity.Product;
import com.example.shoppingmall.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
