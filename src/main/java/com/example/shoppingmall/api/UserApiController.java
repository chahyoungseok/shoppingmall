package com.example.shoppingmall.api;

import com.example.shoppingmall.aop.annotation.RunningTime;
import com.example.shoppingmall.data.dto.RequestJoin;
import com.example.shoppingmall.data.dto.RequestModify;
import com.example.shoppingmall.data.dto.RequestUsername;
import com.example.shoppingmall.data.dto.ResponseUser;
import com.example.shoppingmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserApiController {
    @Autowired
    private UserService userService;

    @RunningTime
    @PostMapping("/join")
    public ResponseEntity<ResponseUser> join(@RequestBody RequestJoin requestJoin){
        ResponseUser responseUser = userService.create(requestJoin);
        return (responseUser != null) ?
                ResponseEntity.status(HttpStatus.OK).body(responseUser) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @GetMapping("/findUser")
    public ResponseEntity<ResponseUser> findUser(RequestUsername requestUsername) {
        System.out.println("controller : "+requestUsername);
        ResponseUser responseUser = userService.findByUsername(requestUsername);
        return (responseUser != null) ?
                ResponseEntity.status(HttpStatus.OK).body(responseUser) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @PutMapping("/updateUser")
    public ResponseEntity<ResponseUser> updateUser(@RequestBody RequestModify requestModify) {
        System.out.println("user_service : "+requestModify);
        ResponseUser responseUser = userService.updateUser(requestModify);
        return (responseUser != null) ?
                ResponseEntity.status(HttpStatus.OK).body(responseUser) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @DeleteMapping("/deleteUser/{username}")
    public String deleteUser(@PathVariable String username) {
        System.out.println("user_controller : "+username);
        userService.deleteUser(username);
        return "redirect:/";
    }

    // Security Example
    @PostMapping("/user")
    public String user(){
        return "example success";
    }


}
