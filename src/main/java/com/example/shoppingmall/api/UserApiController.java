package com.example.shoppingmall.api;

import com.example.shoppingmall.aop.annotation.RunningTime;
import com.example.shoppingmall.data.dto.request.RequestChangePWD;
import com.example.shoppingmall.data.dto.request.RequestJoin;
import com.example.shoppingmall.data.dto.request.RequestModify;
import com.example.shoppingmall.data.dto.request.XssRequestDto;
import com.example.shoppingmall.data.dto.response.ResponseUser;
import com.example.shoppingmall.data.entity.User;
import com.example.shoppingmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
public class UserApiController {

    private final UserService userService;

    @Autowired
    public UserApiController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/join")
    public ResponseEntity<Void> join(@Valid @RequestBody RequestJoin requestJoin){
        ResponseUser responseUser = userService.create(requestJoin);

        return (responseUser != null) ?
                ResponseEntity.status(HttpStatus.OK).body(null) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @GetMapping("/user/mypage")
    public ResponseEntity<ResponseUser> my_page(HttpServletRequest request){
        User user = (User) request.getAttribute("user");

        ResponseUser responseUser = new ResponseUser();
        responseUser.patch(user);

        return ResponseEntity.status(HttpStatus.OK).body(responseUser);
    }

    @PutMapping("/user/update")
    public ResponseEntity<ResponseUser> update(HttpServletRequest request, @Valid @RequestBody RequestModify requestModify) {
        ResponseUser responseUser = userService.updateUser(requestModify, (User) request.getAttribute("user"));
        return (responseUser != null) ?
                ResponseEntity.status(HttpStatus.OK).body(responseUser) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @GetMapping("/check_id/{username}")
    public ResponseEntity<Void> check_id(@PathVariable String username){
        ResponseUser user = userService.findByUsername(username);

        return (user == null) ?
                ResponseEntity.status(HttpStatus.OK).body(null) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @PostMapping("/user/pwd_change")
    public ResponseEntity<Void> change_pwd(HttpServletRequest request, @Valid @RequestBody RequestChangePWD requestChangePWD){
        boolean check_pwd = userService.change_pwd(requestChangePWD, (User) request.getAttribute("user"));

        return (check_pwd) ?
                ResponseEntity.status(HttpStatus.OK).body(null) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    // 삭제 후 제대로 삭제되었는지 확인 후에 여부에따라 200, 400
    @DeleteMapping("/user/delete/{username}")
    public ResponseEntity<Void> delete(HttpServletRequest request, @PathVariable String username) {
        User user = (User) request.getAttribute("user");
        boolean check_delete = userService.deleteUser(username, user.getUsername());

        return (check_delete) ?
                ResponseEntity.status(HttpStatus.OK).body(null) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    // Security Example
    @RunningTime
    @PostMapping("/user")
    public String user(HttpServletRequest request){
        User user = (User) request.getAttribute("user");
        System.out.println(user);
        return "example success";
    }

    @PostMapping("/xss")
    public String xss(@RequestBody XssRequestDto requestDto){

        return requestDto.getContent();
    }

    @PatchMapping("/admin/upgradeAuth/{username}")
    public ResponseEntity<ResponseUser> upgradeAuth(@PathVariable String username){
        // 한번에 많은 유저의 업그레이드를 할 수 있게 할것인가.
        ResponseUser responseUser = userService.upgradeAuth(username);

        return (responseUser != null) ?
                ResponseEntity.status(HttpStatus.OK).body(responseUser) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
}
