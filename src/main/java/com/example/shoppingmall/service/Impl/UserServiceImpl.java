package com.example.shoppingmall.service.Impl;

import com.example.shoppingmall.dao.UserDAO;
import com.example.shoppingmall.data.dto.RequestJoin;
import com.example.shoppingmall.data.dto.ResponseUser;
import com.example.shoppingmall.data.entity.Authority;
import com.example.shoppingmall.data.entity.User;
import com.example.shoppingmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserDAO userDAO;

    @Override
    public ResponseUser create(RequestJoin requestJoin) {

        // RequestJoin -> User Entity
        User user = new User();
        user.setId(null);
        user.setUsername(requestJoin.getUsername());
        user.setNickname(requestJoin.getNickname());
        user.setPassword(bCryptPasswordEncoder.encode(requestJoin.getPassword()));
        user.setTelephone(requestJoin.getTelephone());
        user.setAuthority(Authority.USER);
        user.setE_mail(requestJoin.getE_mail());
        user.setProduct_list(null);

        User created_user = userDAO.createUser(user);
        if (created_user == null) {
            return null;
        }

        // User Entity -> ResponseUser
        ResponseUser responseUser = new ResponseUser();
        responseUser.setId(created_user.getId());
        responseUser.setUsername(created_user.getUsername());
        responseUser.setNickname(created_user.getNickname());
        responseUser.setTelephone(created_user.getTelephone());
        responseUser.setAuthority(created_user.getAuthority());
        responseUser.setE_mail(created_user.getE_mail());

        return responseUser;
    }
}
