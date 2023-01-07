package com.example.shoppingmall.service;

import com.example.shoppingmall.data.dto.request.RequestChangePWD;
import com.example.shoppingmall.data.dto.request.RequestJoin;
import com.example.shoppingmall.data.dto.request.RequestModify;
import com.example.shoppingmall.data.dto.response.ResponseUser;

public interface UserService {
    ResponseUser create(RequestJoin requestJoin);

//    ResponseUser findById(RequestUserId requestUserId);

    ResponseUser findByUsername(String username);

    ResponseUser updateUser(RequestModify requestModify);

    boolean deleteUser(String username);

    boolean change_pwd(RequestChangePWD requestChangePWD);

    ResponseUser upgradeAuth(String username);


}
