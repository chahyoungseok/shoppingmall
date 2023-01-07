package com.example.shoppingmall.data.dto.request;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RequestProduct {
    private String username; // UserID

    private String name; // 상품명

    private int price;

    private String category;

    private String description;

    private String size;

    private String imgKey;
}
