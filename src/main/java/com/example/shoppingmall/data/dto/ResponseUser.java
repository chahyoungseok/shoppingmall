package com.example.shoppingmall.data.dto;


import com.example.shoppingmall.data.entity.Product;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ResponseUser {
    private Long id;

    private String username;

    private String nickname;

    private String telephone;

    private String authority;

    private String e_mail;
}
