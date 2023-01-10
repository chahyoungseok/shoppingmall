package com.example.shoppingmall.repository.cart;

import com.example.shoppingmall.data.entity.Cart;

import java.util.List;

public interface CartRepositoryCustom {

    List<Cart> findAllCart(String username);

    Boolean findSameCart(Long user_id, Long product_id, int state);
}
