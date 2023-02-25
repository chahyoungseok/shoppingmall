package com.example.shoppingmall.data.dto.response;


import com.example.shoppingmall.data.entity.User;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ResponseUser {
    private String username;

    private String nickname;

    private String telephone;

    private String email;

    private String address;

    private String authority;

    @Builder
    public ResponseUser(User user){
        this.username = user.getUsername();
        this.nickname = user.getNickname();
        this.telephone = user.getTelephone();
        this.email = user.getEmail();
        this.address = user.getAddress();
        this.authority = user.getAuthority();
    }
}
