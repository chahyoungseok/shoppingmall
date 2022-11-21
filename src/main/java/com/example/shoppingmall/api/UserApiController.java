package com.example.shoppingmall.api;

import com.example.shoppingmall.data.dto.RequestJoin;
import com.example.shoppingmall.data.dto.ResponseUser;
import com.example.shoppingmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApiController {
    @Autowired
    UserService userService;

    @PostMapping("/join")
    public ResponseEntity<ResponseUser> join(@RequestBody RequestJoin requestJoin){
        ResponseUser responseUser = userService.create(requestJoin);
        return (responseUser != null) ?
                ResponseEntity.status(HttpStatus.OK).body(responseUser) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    // Security Example
    @PostMapping("/user")
    public String user(){
        return "example success";
    }
}
