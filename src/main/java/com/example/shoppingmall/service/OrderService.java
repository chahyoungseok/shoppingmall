package com.example.shoppingmall.service;

import com.example.shoppingmall.data.dto.request.RequestOrder;
import com.example.shoppingmall.data.dto.response.ResponseOrder;
import com.example.shoppingmall.data.entity.User;

import java.util.List;

public interface OrderService {
    List<ResponseOrder> read_order(User user);

    List<ResponseOrder> create_order(User user, RequestOrder requestOrder);
}
