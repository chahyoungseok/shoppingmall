package com.example.shoppingmall.data.dto.queryselect;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class QueryOrderProduct {
    private Long product_id;

    private int count;

    private String size;
}
