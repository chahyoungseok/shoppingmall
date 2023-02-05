package com.example.shoppingmall.data.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ResponseOrder {
    private LocalDateTime order_date;

    private String order_status;

    private int count;

    private int price;

    private String size;

    private String imgKey;

    private boolean stock_zero;
}
