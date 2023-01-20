package com.example.shoppingmall.repository.order;

import com.example.shoppingmall.data.entity.Order;
import com.example.shoppingmall.data.entity.Product;
import com.querydsl.core.types.dsl.BooleanExpression;

import java.util.List;

public interface OrderRepositoryCustom {
    BooleanExpression eqUsername(String username);

    List<Order> selectFromUsername(String username);

    void deleteOrderID(Long id);

    BooleanExpression eqOrderID(Long id);
}
