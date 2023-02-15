package com.example.shoppingmall.data.dto.queryselect;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ReadOrderQuery {
    private Long product_id;

    private int count;

    private int price;

    private String name;

    private String size;

    private String imgKey;

    private boolean stock_zero;
}