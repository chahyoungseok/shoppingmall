package com.example.shoppingmall.data.dto.request;

import com.example.shoppingmall.data.entity.Product;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RequestProductModify {
    private Long id; // product ID (pk)

    @NotEmpty(message = "아이디 입력은 필수 입니다.")
    @Size(min =  4, max = 12, message = "아이디는 최소 4자이상 12자 이하입니다.")
    private String username; // UserID

    @NotEmpty(message = "상품명 입력은 필수 입니다.")
    private String name;

    @NotEmpty(message = "가격 입력은 필수 입니다.")
    private int price;

    @NotEmpty(message = "카테고리 입력은 필수 입니다.")
    private String category;

    @NotEmpty(message = "상세설명 입력은 필수 입니다.")
    private String description;

    @NotEmpty(message = "사이즈 입력은 필수 입니다.")
    private String size;

    @NotEmpty(message = "이미지 키 입력은 필수 입니다.")
    private String imgKey;

    public void toEntity(Product product) {
        product.updateProduct(name,price,category,description,size,imgKey);
    }
}
