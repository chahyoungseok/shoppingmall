package com.example.shoppingmall.dao;

import com.example.shoppingmall.data.entity.User;

public interface UserDAO {
    User createUser(User user);

    /** username(로그인 ID) 로 read */
    User findByUsername(String username);

    User updateUser(User user);

    void deleteUser(String username);

}
