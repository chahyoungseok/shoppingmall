package com.example.shoppingmall.service;

import com.example.shoppingmall.data.dto.request.RequestOrder;
import com.example.shoppingmall.data.dto.response.ResponseOrder;

import java.util.List;

public interface OrderService {
    List<ResponseOrder> read_order(String username);

    List<ResponseOrder> create_order(RequestOrder requestOrder);
}
