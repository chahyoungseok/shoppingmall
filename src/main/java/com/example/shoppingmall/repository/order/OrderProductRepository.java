package com.example.shoppingmall.repository.order;

import com.example.shoppingmall.data.entity.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long>, OrderProductRepositoryCustom{

}
