package com.example.shoppingmall.dao;

import com.example.shoppingmall.data.entity.User;

import java.util.Optional;

public interface UserDAO {
    User createUser(User user);

    User findById(Long id);

    User findByUsername(String username);

    User updateUser(User user);

    void deleteUser(User user);
}
