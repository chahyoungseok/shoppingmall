package com.example.shoppingmall.data.dto.response;


import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ResponseUser {
    private String username;

    private String nickname;

    private String telephone;

    private String e_mail;

    private String address;
}
