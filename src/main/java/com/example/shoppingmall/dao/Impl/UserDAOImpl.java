package com.example.shoppingmall.dao.Impl;

import com.example.shoppingmall.dao.UserDAO;
import com.example.shoppingmall.data.entity.User;
import com.example.shoppingmall.data.repository.UserQueryRepository;
import com.example.shoppingmall.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDAOImpl implements UserDAO {

    private final UserRepository userRepository;

    private final UserQueryRepository userQueryRepository;


    @Autowired
    public UserDAOImpl(UserRepository userRepository, UserQueryRepository userQueryRepository){
        this.userRepository = userRepository;
        this.userQueryRepository = userQueryRepository;
    }
    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userQueryRepository.findByUsername(username);
    }

    @Override
    public User updateUser(User user){
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(String username) {
        userQueryRepository.deleteUser(username);
    }

}
