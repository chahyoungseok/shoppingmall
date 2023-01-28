package com.example.shoppingmall.data.dto.request;

import com.example.shoppingmall.data.dto.queryselect.RequestOrderProduct;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RequestOrder {
    private LocalDateTime order_date;

    private String order_status;

    private List<RequestOrderProduct> requestOrderProductList;
}
