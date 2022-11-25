package com.example.shoppingmall.dao.Impl;

import com.example.shoppingmall.dao.UserDAO;
import com.example.shoppingmall.data.entity.User;
import com.example.shoppingmall.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDAOImpl implements UserDAO {

    private UserRepository userRepository;

    @Autowired
    public UserDAOImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findById(Long id) {
        Optional<User> optional_user = userRepository.findById(id);
        User user = new User();
        if (optional_user.isPresent()) {
            user = optional_user.get();
        }
        return user;
    }

    @Override  // UserRepository 에서 바로 사용하는 것 변경
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User updateUser(User user){
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }
}
