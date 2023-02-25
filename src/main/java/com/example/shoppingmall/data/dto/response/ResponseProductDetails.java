package com.example.shoppingmall.data.dto.response;

import com.example.shoppingmall.data.entity.Product;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseProductDetails {
    private Long id;

    private String name;

    private int price;

    private String category;

    private String description;

    private String size;

    private int favorite;

    private String imgKey;

    private int stock;

    private Boolean check_favorite;

    @Builder
    public ResponseProductDetails(Product product, Boolean status) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.category = product.getCategory();
        this.description = product.getDescription();
        this.size = product.getSize();
        this.favorite = product.getFavorite();
        this.imgKey = product.getImgKey();
        this.check_favorite = status;
        this.stock = product.getStock();
    }
}
