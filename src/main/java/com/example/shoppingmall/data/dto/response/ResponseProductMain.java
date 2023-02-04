package com.example.shoppingmall.data.dto.response;

import com.example.shoppingmall.data.entity.Product;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseProductMain {
    private Long id;

    private String name;

    private int price;

    private String imgKey;

    @Builder
    public ResponseProductMain(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.imgKey = product.getImgKey();
    }
}
