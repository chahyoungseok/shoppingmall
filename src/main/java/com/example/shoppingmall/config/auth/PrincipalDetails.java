package com.example.shoppingmall.config.auth;

import com.example.shoppingmall.data.entity.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Data
public class PrincipalDetails implements UserDetails {
    private User user;

    public PrincipalDetails(User user){
        this.user = user;
    }

    // 해당 User 의 권한을 리턴하는곳!!
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user.getAuthority();
            }
        });
        return collect;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    // 계정 만료에 관한 메서드
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정 Lock에 관한 메서드
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 비밀번호를 오래사용한건 아닌지에 관한 메서드
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {

        // 우리 사이트에서 1년동안 회원이 로그인을 안하면 휴면계정으로 바꾼다.
        // Entity에 마지막으로 로그인을 한 값을 저장하여 1년을 초과하면 return을 false로 설정

        return true;
    }
}
