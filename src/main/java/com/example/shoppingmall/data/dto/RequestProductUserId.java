package com.example.shoppingmall.data.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RequestProductUserId {
    @JsonProperty("user_id")
    private Long userId;
}
