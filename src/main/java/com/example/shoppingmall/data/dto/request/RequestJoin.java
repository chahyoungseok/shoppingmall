package com.example.shoppingmall.data.dto.request;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RequestJoin {
    private String username; // 실제이름
    private String password;
    private String nickname; // 닉네임
    private String telephone;
    private String e_mail;
    private String address;
}
