package com.example.shoppingmall.data.repository;

import com.example.shoppingmall.data.entity.Product;
import com.example.shoppingmall.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.parameters.P;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByUserId(Long user_id);

    /** 제품명으로 찾기 */
    List<Product> findByName(String name);

    List<Product> findByCategory(String category);
}
