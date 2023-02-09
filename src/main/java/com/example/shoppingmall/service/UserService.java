package com.example.shoppingmall.service;

import com.example.shoppingmall.data.dto.request.RequestChangePWD;
import com.example.shoppingmall.data.dto.request.RequestJoin;
import com.example.shoppingmall.data.dto.request.RequestModify;
import com.example.shoppingmall.data.dto.response.ResponseUser;
import com.example.shoppingmall.data.entity.User;

public interface UserService {
    boolean create(RequestJoin requestJoin);

    boolean checkUsername(String username);

    ResponseUser updateUser(RequestModify requestModify, User user);

    boolean deleteUser(String username);

    boolean change_pwd(RequestChangePWD requestChangePWD, User user);

    boolean upgradeAuth(String username);
}
