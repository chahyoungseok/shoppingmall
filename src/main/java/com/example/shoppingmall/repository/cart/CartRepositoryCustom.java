package com.example.shoppingmall.repository.cart;

import com.example.shoppingmall.data.entity.Cart;
import com.querydsl.core.types.dsl.BooleanExpression;

import java.util.List;

public interface CartRepositoryCustom {

    List<Cart> findAllCart(String username);

    Boolean findSameCart(Long user_id, Long product_id, int state);

    BooleanExpression eqUserID(Long id);

    BooleanExpression eqProductID(Long id);

    BooleanExpression eqCartID(Long id);

    void deleteProductID(Long id);

    void deleteCartID(Long id);

    Cart selectFromUserID_N_ProductID(Long user_id, Long product_id);

    void updateCartID(Long id, int updated_value);
}
