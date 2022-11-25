package com.example.shoppingmall.data.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RequestModify {
    private String username; // 실제이름
    private String password;
    private String nickname; // 닉네임
    private String telephone;
    private String e_mail;
}
