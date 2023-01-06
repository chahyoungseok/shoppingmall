package com.example.shoppingmall.data.dto.request;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RequestProductUserId {
    private String username; // UserID
}
