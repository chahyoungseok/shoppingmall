package com.example.shoppingmall.data.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RequestProductModify {
    private Long id; // product ID (pk)

    private String name;

    private int price;

    private String category;

    private String description;

    private String size;
}
