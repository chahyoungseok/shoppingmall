package com.example.shoppingmall.service.Impl;

import com.example.shoppingmall.data.dto.request.RequestChangePWD;
import com.example.shoppingmall.data.dto.request.RequestJoin;
import com.example.shoppingmall.data.dto.request.RequestModify;
import com.example.shoppingmall.data.dto.response.ResponseUser;
import com.example.shoppingmall.data.entity.Authority;
import com.example.shoppingmall.data.entity.User;
import com.example.shoppingmall.repository.user.UserRepository;
import com.example.shoppingmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(BCryptPasswordEncoder bCryptPasswordEncoder, UserRepository userRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public boolean create(RequestJoin requestJoin) {
        // username 중복 확인
        boolean check_user = checkUsername(requestJoin.getUsername());
        boolean check_telephone = checkTelephone(requestJoin.getTelephone());
        boolean check_email = checkEmail(requestJoin.getEmail());

        if (check_user || check_telephone || check_email) {
            return false;
        }

        User user = requestJoin.toEntity(bCryptPasswordEncoder.encode(requestJoin.getPassword()));

        User created_user = userRepository.save(user);
        return !created_user.getUsername().isEmpty();
    }

    @Override
    public boolean checkUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean checkTelephone(String telephone) {
        return userRepository.existsByTelephone(telephone);
    }

    @Override
    public boolean checkEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public ResponseUser updateUser(RequestModify requestModify, User user) {
        boolean check_telephone = checkTelephone(requestModify.getTelephone());
        boolean check_email = checkEmail(requestModify.getEmail());

        if ((check_telephone && !user.getTelephone().equals(requestModify.getTelephone()))
                || (check_email && !user.getEmail().equals(requestModify.getEmail()))
                || !requestModify.getUsername().equals(user.getUsername())) {
            return null;
        }

        requestModify.toEntity(user);

        User modified_user = userRepository.save(user);

        return ResponseUser.builder().user(modified_user).build();
    }

    @Override
    public boolean deleteUser(String username) {
        userRepository.deleteByUser(username);

        boolean check = checkUsername(username);
        return !check;
    }

    @Override
    public boolean change_pwd(RequestChangePWD requestChangePWD, User user){
        if(bCryptPasswordEncoder.matches(requestChangePWD.getOrigin_password(), user.getPassword())){
            user.changePWD(bCryptPasswordEncoder.encode(requestChangePWD.getNew_password()));
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean upgradeAuth(String username) {
        User user = userRepository.findByUsername(username);

        if(user == null || !user.getAuthority().equals(Authority.USER)) {return false;}

        user.upgradeAuth(Authority.REGISTER);

        User modified_user = userRepository.save(user);

        return !modified_user.getUsername().isEmpty();
    }
}
