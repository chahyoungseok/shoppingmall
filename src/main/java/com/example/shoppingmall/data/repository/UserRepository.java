package com.example.shoppingmall.data.repository;

import com.example.shoppingmall.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    // findBy 규칙 -> Username 문법
    // select * from user where username = 1?
    public User findByUsername(String username);
}