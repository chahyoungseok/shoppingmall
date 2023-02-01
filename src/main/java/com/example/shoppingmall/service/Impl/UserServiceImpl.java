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
        User check_username = userRepository.findByUsername(requestJoin.getUsername());
        if(check_username != null) {
            return false;
        }

        // RequestJoin -> User Entity
        User user = User.builder()
                .id(null)
                .username(requestJoin.getUsername())
                .nickname(requestJoin.getNickname())
                .password(bCryptPasswordEncoder.encode(requestJoin.getPassword()))
                .telephone(requestJoin.getTelephone())
                .e_mail(requestJoin.getE_mail())
                .address(requestJoin.getAddress())
                .authority(Authority.USER)
                .build();

        User created_user = userRepository.save(user);

        return !created_user.getUsername().isEmpty();
    }

    @Override
    public boolean findByUsername(String username) {
        // Dto -> Entity
        User user = userRepository.findByUsername(username);
        return user == null;
    }

    @Override
    public ResponseUser updateUser(RequestModify requestModify, User user) {
        if (!requestModify.getUsername().equals(user.getUsername())) {
            return null;
        }

        // Dto -> Entity
        user.updateUser(
                requestModify.getNickname(),
                requestModify.getTelephone(),
                requestModify.getE_mail(),
                requestModify.getAddress()
        );

        User modified_user = userRepository.save(user);

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
    public boolean deleteUser(String username) {
        userRepository.deleteByUser(username);

        User check_delete = userRepository.findByUsername(username);

        return check_delete == null;
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
        // Dto -> Entity
        User user = userRepository.findByUsername(username);
        if(user == null) {return false;}

        user.upgradeAuth("ROLE_REGISTER");

        User modified_user = userRepository.save(user);

        return !modified_user.getUsername().isEmpty();
    }
}
