package com.example.shoppingmall.data.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class JoinDTO {
    private String username; // 닉네임
    private String password;
    private String name; // 실제이름
    private String telephone;
    private String e_mail;
}
