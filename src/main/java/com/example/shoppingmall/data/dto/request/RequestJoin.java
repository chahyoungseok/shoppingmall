package com.example.shoppingmall.data.dto.request;

import com.example.shoppingmall.data.entity.Authority;
import com.example.shoppingmall.data.entity.User;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RequestJoin {

    @NotEmpty(message = "아이디 입력은 필수 입니다.")
    @Size(min =  4, max = 12, message = "아이디는 최소 4자이상 12자 이하입니다.")
    private String username;

    @NotEmpty(message = "비밀번호 입력은 필수 입니다.")
    @Size(min =  8, max = 16, message = "비밀번호는 최소 8자이상 16자 이하입니다.")
    private String password;

    @NotEmpty(message = "닉네임 입력은 필수 입니다.")
    private String nickname;

    @NotEmpty(message = "전화번호 입력은 필수 입니다.")
    private String telephone;

    @NotEmpty(message = "이메일 입력은 필수 입니다.")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식에 맞지 않습니다.")
    private String email;

    @NotEmpty(message = "주소 입력은 필수 입니다.")
    private String address;

    public User toEntity(String password) {

        return User.builder()
                .id(null)
                .username(username)
                .nickname(nickname)
                .password(password)
                .telephone(telephone)
                .email(email)
                .address(address)
                .authority(Authority.USER)
                .build();
    }
}
