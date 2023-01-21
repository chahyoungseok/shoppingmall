package com.example.shoppingmall.data.dto.request;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RequestProduct {
    @NotEmpty(message = "상품명 입력은 필수 입니다.")
    private String name; // 상품명

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
}
