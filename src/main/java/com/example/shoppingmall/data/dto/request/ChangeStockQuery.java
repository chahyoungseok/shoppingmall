package com.example.shoppingmall.data.dto.request;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ChangeStockQuery {
    private Long product_id;

    private int stock;
}
