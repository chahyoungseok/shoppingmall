package com.example.shoppingmall.data.dto.request;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RequestGeneric<RD> {

    @NotEmpty
    private RD rd;
}
