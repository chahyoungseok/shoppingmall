package com.example.shoppingmall.repository.product;

import com.example.shoppingmall.data.dto.queryselect.SelectIDQuery;
import com.example.shoppingmall.data.entity.Product;
import com.querydsl.core.types.dsl.BooleanExpression;

import java.util.List;

public interface ProductRepositoryCustom {
    BooleanExpression eqUsername(String username);

    List<SelectIDQuery> selectIDFromUsername(String username);

    BooleanExpression eqProductIDList(List<Long> IDList);

    void deleteProductID(List<Long> IDList);

    List<Product> findByIdList(List<Long> IDList);
}
