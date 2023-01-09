package com.example.shoppingmall.data.repository.user;

import com.example.shoppingmall.data.entity.User;

public interface UserRepositoryCustom {

    void deleteByUser(String username);
    User findByUsername(String username);
}
