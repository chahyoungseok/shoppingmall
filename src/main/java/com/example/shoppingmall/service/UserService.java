package com.example.shoppingmall.service;

import com.example.shoppingmall.data.dto.RequestJoin;
import com.example.shoppingmall.data.dto.RequestModify;
import com.example.shoppingmall.data.dto.RequestUsername;
import com.example.shoppingmall.data.dto.ResponseUser;

public interface UserService {
    ResponseUser create(RequestJoin requestJoin);

//    ResponseUser findById(RequestUserId requestUserId);

    ResponseUser findByUsername(RequestUsername requestUsername);

    ResponseUser updateUser(RequestModify requestModify);

    void deleteUser(String username);

    ResponseUser upgradeAuth(String username);
}
