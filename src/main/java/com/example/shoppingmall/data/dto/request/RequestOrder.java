package com.example.shoppingmall.data.dto.request;

import com.example.shoppingmall.data.dto.queryselect.QueryOrderProduct;
import com.example.shoppingmall.data.entity.Order;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RequestOrder {
    private String order_date;

    private String order_status;

    private List<QueryOrderProduct> queryOrderProductList = new ArrayList<>();

    public Order toEntity() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        return Order.builder()
                .id(null)
                .orderDate(LocalDateTime.parse(order_date, formatter))
                .orderStatus(order_status)
                .user(null)
                .build();
    }
}
