package com.example.shoppingmall.service;

import com.example.shoppingmall.data.dto.request.RequestChangePWD;
import com.example.shoppingmall.data.dto.request.RequestJoin;
import com.example.shoppingmall.data.dto.request.RequestModify;
import com.example.shoppingmall.data.dto.response.ResponseUser;
import com.example.shoppingmall.data.entity.User;

public interface UserService {
    ResponseUser create(RequestJoin requestJoin);

//    ResponseUser findById(RequestUserId requestUserId);

    ResponseUser findByUsername(String username);

    ResponseUser updateUser(RequestModify requestModify, User user);

    boolean deleteUser(String username, String real_username);

    boolean change_pwd(RequestChangePWD requestChangePWD, User user);

    ResponseUser upgradeAuth(String username);

}
