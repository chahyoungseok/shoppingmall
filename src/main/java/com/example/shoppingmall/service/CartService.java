package com.example.shoppingmall.service;

import com.example.shoppingmall.data.dto.response.ResponseCart;

import java.util.List;

public interface CartService {
    List<ResponseCart> readCart(String username);

    List<ResponseCart> createCart(String username, Long product_id);

    List<ResponseCart> deleteCart(String username, Long product_id);
}
