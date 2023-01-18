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
    public ResponseUser create(RequestJoin requestJoin) {
        // username 중복 확인
        User check_username = userRepository.findByUsername(requestJoin.getUsername());
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

        User created_user = userRepository.save(user);
        if (created_user.getUsername().isEmpty()) {return null;}

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
        User user = userRepository.findByUsername(username);
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
    public ResponseUser updateUser(RequestModify requestModify, User user) {
        if (!requestModify.getUsername().equals(user.getUsername())) {
            return null;
        }
        //해당 user는 검증을 통해 얻어온 Entity 이기에 null일 수 가 없습니다.

        // Dto -> Entity
        user.setUsername(requestModify.getUsername());
        user.setNickname(requestModify.getNickname());
        user.setTelephone(requestModify.getTelephone());
        user.setAddress(requestModify.getAddress());
        user.setE_mail(requestModify.getE_mail());

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
    public boolean deleteUser(String username, String real_username) {
        if (!username.equals(real_username)){
            return false;
        }
        userRepository.deleteByUser(username);

        User check_delete = userRepository.findByUsername(username);

        if(check_delete == null) {return true;}
        return false;
    }

    @Override
    public boolean change_pwd(RequestChangePWD requestChangePWD, User user){
        if(bCryptPasswordEncoder.matches(requestChangePWD.getOrigin_password(), user.getPassword())){
            user.setPassword(bCryptPasswordEncoder.encode(requestChangePWD.getNew_password()));
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public ResponseUser upgradeAuth(String username) {
        // Dto -> Entity
        User user = userRepository.findByUsername(username);
        if(user == null) {return null;}

        user.setAuthority("ROLE_REGISTER");
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
}
