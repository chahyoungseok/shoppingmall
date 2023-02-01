package com.example.shoppingmall.data.dto.queryselect;

import com.example.shoppingmall.data.entity.Product;
import com.example.shoppingmall.data.entity.User;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SelectCart {
    private Long id;

    private int count;

    private User user;

    private Product product;

}
