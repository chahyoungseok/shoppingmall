package com.example.shoppingmall.data.dto.response;

import com.example.shoppingmall.data.entity.Product;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseProduct {
    private Long id;

    private String name;

    private int price;

    private String category;

    private String description;

    private String size;

    private int favorite;

    private String imgKey;

    private int stock;

    @Builder
    public ResponseProduct(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.category = product.getCategory();
        this.description = product.getDescription();
        this.size = product.getSize();
        this.favorite = product.getFavorite();
        this.imgKey = product.getImgKey();
        this.stock = (product.getStock() >= 10) ? -1 : product.getStock();
    }
}
