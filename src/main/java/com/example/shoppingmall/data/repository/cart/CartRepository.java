package com.example.shoppingmall.data.repository.cart;

import com.example.shoppingmall.data.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long>, CartRepositoryCustom {
}
