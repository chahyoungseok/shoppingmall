package com.example.shoppingmall.data.dto.queryselect;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ReadOrderQuery {
    private int count;

    private int price;

    private String size;

    private String imgKey;

    private boolean stock_zero;
}