package com.example.shoppingmall.data.dto.request;

import com.example.shoppingmall.data.entity.Product;
import com.example.shoppingmall.data.entity.User;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    @NotEmpty
    private String date;

    public Product toEntity(User user) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        return Product.builder()
                .id(null)
                .name(name)
                .price(price)
                .category(category)
                .description(description)
                .size(size)
                .imgKey(imgKey)
                .date(LocalDateTime.parse(date, formatter))
                .hits(0)
                .favorite(0)
                .user(user)
                .build();
    }
}
