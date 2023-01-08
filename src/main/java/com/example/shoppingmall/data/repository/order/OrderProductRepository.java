package com.example.shoppingmall.data.repository.order;

import com.example.shoppingmall.data.entity.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
}
