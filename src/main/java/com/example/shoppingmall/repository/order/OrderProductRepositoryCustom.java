package com.example.shoppingmall.repository.order;

import com.querydsl.core.types.dsl.BooleanExpression;

public interface OrderProductRepositoryCustom {
    BooleanExpression eqOrderID(Long id);

    void deleteOrderID(Long id);
}
