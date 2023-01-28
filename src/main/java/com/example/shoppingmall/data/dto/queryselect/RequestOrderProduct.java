package com.example.shoppingmall.data.dto.queryselect;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RequestOrderProduct {
    private Long product_id;

    private int count;
}
