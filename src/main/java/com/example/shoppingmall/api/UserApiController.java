package com.example.shoppingmall.api;

import com.example.shoppingmall.aop.annotation.RunningTime;
import com.example.shoppingmall.data.dto.request.*;
import com.example.shoppingmall.data.dto.response.ResponseUser;
import com.example.shoppingmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserApiController {
    @Autowired
    private UserService userService;

    @PostMapping("/join")
    public ResponseEntity<ResponseUser> join(@RequestBody RequestJoin requestJoin){
        ResponseUser responseUser = userService.create(requestJoin);

        return (responseUser != null) ?
                ResponseEntity.status(HttpStatus.OK).body(responseUser) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @GetMapping("/user/mypage")
    public ResponseEntity<ResponseUser> my_page(HttpServletRequest request){
        Object object = request.getAttribute("username");
        RequestUsername requestUsername = new RequestUsername();
        requestUsername.setUsername(object.toString());

        ResponseUser responseUser = userService.findByUsername(requestUsername);

        return (responseUser != null) ?
                ResponseEntity.status(HttpStatus.OK).body(responseUser) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }


//    쓰임이 없어 일단 주석
//    @GetMapping("/findUser")
//    public ResponseEntity<ResponseUser> findUser(@RequestBody RequestUsername requestUsername) {
//        ResponseUser responseUser = userService.findByUsername(requestUsername);
//        return (responseUser != null) ?
//                ResponseEntity.status(HttpStatus.OK).body(responseUser) :
//                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//    }

    @PutMapping("/user/update")
    public ResponseEntity<ResponseUser> update(@RequestBody RequestModify requestModify) {
        ResponseUser responseUser = userService.updateUser(requestModify);
        return (responseUser != null) ?
                ResponseEntity.status(HttpStatus.OK).body(responseUser) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @PostMapping("/check_id")
    public ResponseEntity<Boolean> check_id(@RequestBody RequestUsername requestUsername){
        ResponseUser user = userService.findByUsername(requestUsername);

        return (user == null) ?
                ResponseEntity.status(HttpStatus.OK).body(true) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
    }

    @PostMapping("/user/pwd_change")
    public ResponseEntity<Boolean> pwd_change(HttpServletRequest request, @RequestBody RequestChangePWD requestChangePWD){
        requestChangePWD.setUsername(request.getAttribute("username").toString());

        boolean check_pwd = userService.change_pwd(requestChangePWD);
        return (check_pwd) ?
                ResponseEntity.status(HttpStatus.OK).body(true) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
    }

    // 삭제 후 제대로 삭제되었는지 확인 후에 여부에따라 200, 400
    @DeleteMapping("/user/delete")
    public ResponseEntity<Boolean> delete(@RequestBody RequestUsername requestUsername) {
        boolean check_delete = userService.deleteUser(requestUsername.getUsername());
        return (check_delete) ?
                ResponseEntity.status(HttpStatus.OK).body(true) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
    }


    // Security Example
    @RunningTime
    @PostMapping("/user")
    public String user(HttpServletRequest request){
        Object object = request.getAttribute("username");
        System.out.println(object.toString());
        return "example success";
    }


    @PostMapping("/admin/upgradeAuth")
    public ResponseEntity<ResponseUser> upgradeAuth(@RequestBody RequestUsername requestUsername){
        // 한번에 많은 유저의 업그레이드를 할 수 있게 할것인가.
        ResponseUser responseUser = userService.upgradeAuth(requestUsername.getUsername());

        return (responseUser != null) ?
                ResponseEntity.status(HttpStatus.OK).body(responseUser) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
}
