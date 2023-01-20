package com.example.shoppingmall.repository.product;

import com.example.shoppingmall.data.entity.Product;
import com.querydsl.core.types.dsl.BooleanExpression;

import java.util.List;

public interface ProductRepositoryCustom {
    BooleanExpression eqUsername(String username);

    List<Product> selectFromUsername(String username);

    BooleanExpression eqProductID(Long id);

    void deleteProductID(Long id);
}
