package com.example.shoppingmall.data.dto.response;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ResponseProduct {
    private Long id;

    private String name;

    private int price;

    private String category;

    private String description;

    private String size;

    private String imgKey;
}
