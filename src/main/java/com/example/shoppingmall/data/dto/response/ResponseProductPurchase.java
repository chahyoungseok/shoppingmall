package com.example.shoppingmall.data.dto.response;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ResponseProductPurchase implements Comparable<ResponseProductPurchase>{
    private Long id;

    private String name;

    private int price;

    private int favorite;

    private String imgKey;

    private int count;

    @Override
    public int compareTo(ResponseProductPurchase productPurchase) {
        if(productPurchase.count > count){
            return 1;
        } else if (productPurchase.count < count) {
            return -1;
        }
        return 0;
    }
}
