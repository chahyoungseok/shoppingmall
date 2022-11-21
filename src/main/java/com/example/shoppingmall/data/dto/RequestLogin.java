package com.example.shoppingmall.data.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RequestLogin {
    private String username;
    private String password;
}
