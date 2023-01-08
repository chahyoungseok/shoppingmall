package com.example.shoppingmall.data.repository;

import com.example.shoppingmall.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}