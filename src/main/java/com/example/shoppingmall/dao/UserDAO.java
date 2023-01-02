package com.example.shoppingmall.dao;

import com.example.shoppingmall.data.entity.Product;
import com.example.shoppingmall.data.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO {
    User createUser(User user);

    /** username(로그인 ID) 로 read */
    User findByUsername(String username);

    User updateUser(User user);

    void deleteUser(User user);

}
