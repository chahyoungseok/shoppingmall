package com.example.shoppingmall.repository.order;

import com.example.shoppingmall.data.dto.queryselect.ReadOrderQuery;
import com.example.shoppingmall.data.entity.OrderProduct;
import com.querydsl.core.types.dsl.BooleanExpression;

import java.util.List;

public interface OrderProductRepositoryCustom {
    BooleanExpression eqOrderIDList(List<Long> orderIDList);

    void deleteOrderIDList(List<Long> IDList);

    List<ReadOrderQuery> findResponseOrder(List<OrderProduct> orderProductList);
}
