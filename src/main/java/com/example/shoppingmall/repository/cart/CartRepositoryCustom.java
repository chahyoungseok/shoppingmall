package com.example.shoppingmall.repository.cart;

import com.example.shoppingmall.data.dto.queryselect.SelectCart;
import com.example.shoppingmall.data.dto.response.ResponseCart;
import com.querydsl.core.types.dsl.BooleanExpression;

import java.util.List;

public interface CartRepositoryCustom {

    List<ResponseCart> findAllCart(String username);

    Boolean findSameCart(Long user_id, Long product_id, int state);

    BooleanExpression eqUserID(Long id);

    BooleanExpression eqProductID(Long id);

    BooleanExpression eqCartID(Long id);

    BooleanExpression eqProductIDList(List<Long> IDList);

    void deleteProductID(List<Long> IDList);

    void deleteCartID(Long id);

    SelectCart selectFromUserID_N_ProductID(Long user_id, Long product_id);

    void updateCartID(Long id, int updated_value);
}
