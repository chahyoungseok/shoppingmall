package com.example.shoppingmall.data.dto.response;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ResponseCart {
    private Long id;

    private String name;

    private int price;

    private String size;

    private String imgKey;

    private int count;

    private boolean stock_zero;
}
