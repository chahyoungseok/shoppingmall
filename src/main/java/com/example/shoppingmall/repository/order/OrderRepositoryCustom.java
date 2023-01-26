package com.example.shoppingmall.repository.order;

import com.example.shoppingmall.data.dto.queryselect.SelectIDQuery;
import com.querydsl.core.types.dsl.BooleanExpression;

import java.util.List;

public interface OrderRepositoryCustom {
    BooleanExpression eqUsername(String username);

    List<SelectIDQuery> selectIDFromUsername(String username);

    void deleteOrderID(List<Long> IDList);

    BooleanExpression eqOrderIDList(List<Long> IDList);
}
