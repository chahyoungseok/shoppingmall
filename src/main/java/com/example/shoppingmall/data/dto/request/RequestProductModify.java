package com.example.shoppingmall.data.dto.request;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RequestProductModify {
    private Long id; // product ID (pk)

    private String username; // UserID

    private String name;

    private int price;

    private String category;

    private String description;

    private String size;

    private String imgKey;
}
