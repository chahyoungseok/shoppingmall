package com.example.shoppingmall.data.dto.queryselect;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SelectProductStockQuery {
    private Long product_id;

    private int stock;

    private Long user_id;
}
