package com.example.shoppingmall.repository.cart;

import com.example.shoppingmall.data.dto.queryselect.SelectCart;
import com.example.shoppingmall.data.dto.response.ResponseCart;
import com.querydsl.core.types.dsl.BooleanExpression;

import java.util.List;

public interface CartRepositoryCustom {

    List<ResponseCart> findAllCart(String username);

    Boolean findSameCart(Long user_id, Long product_id, String size, int state);

    BooleanExpression eqUserID(Long id);

    BooleanExpression eqUsername(String username);

    BooleanExpression eqProductID(Long id);

    BooleanExpression eqSize(String size);

    BooleanExpression eqCartID(Long id);

    void deleteCartID(Long id);

    Boolean deleteAllCart(Long user_id, Long product_id, String size);

    SelectCart selectFromUserID_N_ProductID(Long user_id, Long product_id, String size);

    void updateCartID(Long id, int updated_value);
}
