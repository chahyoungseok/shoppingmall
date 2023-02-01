package com.example.shoppingmall.data.dto.request;

import com.example.shoppingmall.data.dto.queryselect.QueryOrderProduct;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RequestOrder {
    private String order_date;

    private String order_status;

    private List<QueryOrderProduct> queryOrderProductList;
}
