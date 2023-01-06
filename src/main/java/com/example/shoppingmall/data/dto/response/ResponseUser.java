package com.example.shoppingmall.data.dto.response;


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
    private String username;

    private String nickname;

    private String telephone;

    private String e_mail;

    private String address;
}
