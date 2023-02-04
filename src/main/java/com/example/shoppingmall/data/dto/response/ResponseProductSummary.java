package com.example.shoppingmall.data.dto.response;

import com.example.shoppingmall.data.entity.Product;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseProductSummary {
    private Long id;

    private String name;

    private int price;

    private int favorite;

    private String imgKey;

    @Builder
    public ResponseProductSummary(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.favorite = product.getFavorite();
        this.imgKey = product.getImgKey();
    }

    @Builder(builderMethodName = "dtoBuilder")
    public ResponseProductSummary(ResponseProductPurchase responseProductPurchase) {
        this.id = responseProductPurchase.getId();
        this.name = responseProductPurchase.getName();
        this.price = responseProductPurchase.getPrice();
        this.favorite = responseProductPurchase.getFavorite();
        this.imgKey = responseProductPurchase.getImgKey();
    }
}
