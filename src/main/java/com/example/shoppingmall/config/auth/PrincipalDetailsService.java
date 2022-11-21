package com.example.shoppingmall.config.auth;

import com.example.shoppingmall.data.entity.User;
import com.example.shoppingmall.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// http://localhost:8080/login => .formLogin().disable() 이므로 동작안함
@Service
public class PrincipalDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    // 시큐리티 session = Authentication = userDetails
    @Override // 파라미터의 username은 HTML코드에서 name의 값을 그대로 반영하기 때문에 form에서 name의 값을 username으로 설정해줘야한다.
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("PrincipalDetailsService.username : " + username);
        User userEntity = userRepository.findByUsername(username);
        if(userEntity != null){
            return new PrincipalDetails(userEntity);
        }
        return null;
    }
}
