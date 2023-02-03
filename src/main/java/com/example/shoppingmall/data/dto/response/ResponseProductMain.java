package com.example.shoppingmall.data.dto.response;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ResponseProductMain {
    private Long id;

    private String name;

    private int price;

    private String imgKey;
}
