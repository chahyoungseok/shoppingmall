package com.example.shoppingmall.data.dto.request;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RequestChangePWD {
    private String username;
    private String origin_password;
    private String new_password;
}
