package com.example.shoppingmall.data.dto.request;

import com.example.shoppingmall.data.entity.User;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RequestModify {

    @NotEmpty(message = "닉네임 입력은 필수 입니다.")
    private String nickname;

    @NotEmpty(message = "전화번호 입력은 필수 입니다.")
    private String telephone;

    @NotEmpty(message = "이메일 입력은 필수 입니다.")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식에 맞지 않습니다.")
    private String email;

    @NotEmpty(message = "주소 입력은 필수 입니다.")
    private String address;

    public void toEntity(User user) {
        user.updateUser(nickname, telephone, email, address);
    }
}
