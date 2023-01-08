package com.example.shoppingmall.service.Impl;

import com.example.shoppingmall.dao.UserDAO;
import com.example.shoppingmall.data.dto.request.RequestChangePWD;
import com.example.shoppingmall.data.dto.request.RequestJoin;
import com.example.shoppingmall.data.dto.request.RequestModify;
import com.example.shoppingmall.data.dto.response.ResponseUser;
import com.example.shoppingmall.data.entity.Authority;
import com.example.shoppingmall.data.entity.User;
import com.example.shoppingmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserDAO userDAO;

    @Autowired
    public UserServiceImpl(BCryptPasswordEncoder bCryptPasswordEncoder, UserDAO userDAO) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userDAO = userDAO;
    }

    @Override
    public ResponseUser create(RequestJoin requestJoin) {
        // username 중복 확인
        User check_username = userDAO.findByUsername(requestJoin.getUsername());
        if(check_username != null) {
            return null;
        }

        // RequestJoin -> User Entity
        User user = new User();
        user.setId(null);
        user.setUsername(requestJoin.getUsername());
        user.setNickname(requestJoin.getNickname());
        user.setPassword(bCryptPasswordEncoder.encode(requestJoin.getPassword()));
        user.setTelephone(requestJoin.getTelephone());
        user.setAuthority(Authority.USER);
        user.setE_mail(requestJoin.getE_mail());
        user.setAddress(requestJoin.getAddress());

        User created_user = userDAO.createUser(user);
        if (created_user == null) {return null;}

        // User Entity -> ResponseUser
        ResponseUser responseUser = new ResponseUser();
        responseUser.setUsername(created_user.getUsername());
        responseUser.setNickname(created_user.getNickname());
        responseUser.setTelephone(created_user.getTelephone());
        responseUser.setE_mail(created_user.getE_mail());
        responseUser.setAddress(created_user.getAddress());

        return responseUser;
    }

    @Override
    public ResponseUser findByUsername(String username) {
        // Dto -> Entity
        User user = userDAO.findByUsername(username);
        if (user == null){return null;}

        // Entity -> Dto
        ResponseUser responseUser = new ResponseUser();
        responseUser.setUsername(user.getUsername());
        responseUser.setNickname(user.getNickname());
        responseUser.setTelephone(user.getTelephone());
        responseUser.setE_mail(user.getE_mail());
        responseUser.setAddress(user.getAddress());
        return responseUser;
    }

    @Override
    public ResponseUser updateUser(RequestModify requestModify, String username) {
        if (!requestModify.getUsername().equals(username)) {
            return null;
        }

        // Dto -> Entity
        User user = userDAO.findByUsername(requestModify.getUsername());
        if(user == null) {return null;}

        user.setUsername(requestModify.getUsername());
        user.setNickname(requestModify.getNickname());
        user.setTelephone(requestModify.getTelephone());
        user.setAddress(requestModify.getAddress());
        user.setE_mail(requestModify.getE_mail());

        User modified_user = userDAO.updateUser(user);

        // Entity -> Dto
        ResponseUser responseUser = new ResponseUser();
        responseUser.setUsername(modified_user.getUsername());
        responseUser.setNickname(modified_user.getNickname());
        responseUser.setTelephone(modified_user.getTelephone());
        responseUser.setAddress(modified_user.getAddress());
        responseUser.setE_mail(modified_user.getE_mail());

        return responseUser;
    }

    @Override
    public boolean deleteUser(String username, String real_username) {
        if (!username.equals(real_username)){
            return false;
        }
        userDAO.deleteUser(username);

        User check_delete = userDAO.findByUsername(username);

        if(check_delete == null) {return true;}
        return false;
    }

    @Override
    public boolean change_pwd(RequestChangePWD requestChangePWD){
        User check_pwd = userDAO.findByUsername(requestChangePWD.getUsername());

        if(check_pwd == null) {return false;}

        if(bCryptPasswordEncoder.matches(requestChangePWD.getOrigin_password(), check_pwd.getPassword())){
            check_pwd.setPassword(bCryptPasswordEncoder.encode(requestChangePWD.getNew_password()));
            userDAO.updateUser(check_pwd);
            return true;
        }
        return false;
    }

    @Override
    public ResponseUser upgradeAuth(String username) {
        // Dto -> Entity
        User user = userDAO.findByUsername(username);
        if(user == null) {return null;}

        user.setAuthority("ROLE_REGISTER");
        User modified_user = userDAO.updateUser(user);

        // Entity -> Dto
        ResponseUser responseUser = new ResponseUser();
        responseUser.setUsername(modified_user.getUsername());
        responseUser.setNickname(modified_user.getNickname());
        responseUser.setTelephone(modified_user.getTelephone());
        responseUser.setAddress(modified_user.getAddress());
        responseUser.setE_mail(modified_user.getE_mail());

        return responseUser;
    }
}
