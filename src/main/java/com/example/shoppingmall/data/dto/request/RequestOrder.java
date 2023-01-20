package com.example.shoppingmall.data.dto.request;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RequestOrder {
    private LocalDateTime order_date;

    private String order_status;

    private int count;

    private Long product_id;
}
