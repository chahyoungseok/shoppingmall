package com.example.shoppingmall.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ResponseProduct {
    private String name;

    private int price;

    private String category;

    private String description;

    private String size;
}
