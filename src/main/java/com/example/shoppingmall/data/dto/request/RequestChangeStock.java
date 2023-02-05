package com.example.shoppingmall.data.dto.request;

import com.example.shoppingmall.data.dto.queryselect.ChangeStockQuery;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RequestChangeStock {
    private List<ChangeStockQuery> changeStockQueryList = new ArrayList<>();
}
