package com.example.shoppingmall.service;

import com.example.shoppingmall.data.dto.RequestJoin;
import com.example.shoppingmall.data.dto.ResponseUser;

public interface UserService {
    public ResponseUser create(RequestJoin requestJoin);
}
