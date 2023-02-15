package com.example.shoppingmall.data.dto.response;

import com.example.shoppingmall.data.dto.queryselect.ReadOrderQuery;
import com.example.shoppingmall.data.entity.Order;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ResponseOrder {
    private Long product_id;

    private LocalDateTime order_date;

    private String order_status;

    private String name;

    private int count;

    private int price;

    private String size;

    private String imgKey;

    private boolean stock_zero;

    @Builder
    public ResponseOrder(Order order, ReadOrderQuery readOrderQuery) {
        this.product_id = readOrderQuery.getProduct_id();
        this.order_date = order.getOrderDate();
        this.order_status = order.getOrderStatus();
        this.name = readOrderQuery.getName();
        this.count = readOrderQuery.getCount();
        this.price = readOrderQuery.getPrice();
        this.size = readOrderQuery.getSize();
        this.imgKey = readOrderQuery.getImgKey();
        this.stock_zero = readOrderQuery.isStock_zero();
    }
}
