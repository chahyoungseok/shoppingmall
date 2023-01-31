package com.example.shoppingmall.api;

import com.example.shoppingmall.data.dto.request.RequestChangePWD;
import com.example.shoppingmall.data.dto.request.RequestJoin;
import com.example.shoppingmall.data.dto.request.RequestModify;
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
        boolean check = userService.create(requestJoin);

        return (check) ?
                ResponseEntity.status(HttpStatus.OK).body(null) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @GetMapping("/user")
    public ResponseEntity<ResponseUser> my_page(HttpServletRequest request){
        User user = (User) request.getAttribute("user");

        ResponseUser responseUser = new ResponseUser();
        responseUser.patch(user);

        return ResponseEntity.status(HttpStatus.OK).body(responseUser);
    }

    @PutMapping("/user")
    public ResponseEntity<ResponseUser> update(HttpServletRequest request, @Valid @RequestBody RequestModify requestModify) {
        ResponseUser responseUser = userService.updateUser(requestModify, (User) request.getAttribute("user"));
        return (responseUser != null) ?
                ResponseEntity.status(HttpStatus.OK).body(responseUser) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @GetMapping("/check_id/{username}")
    public ResponseEntity<Void> check_id(@PathVariable String username){
        boolean check = userService.findByUsername(username);

        return (check) ?
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
    @DeleteMapping("/user")
    public ResponseEntity<Void> delete(HttpServletRequest request) {
        User user = (User) request.getAttribute("user");
        boolean check_delete = userService.deleteUser(user.getUsername());

        return (check_delete) ?
                ResponseEntity.status(HttpStatus.OK).body(null) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @PatchMapping("/admin/upgradeAuth/{username}")
    public ResponseEntity<Void> upgradeAuth(@PathVariable String username){
        // 한번에 많은 유저의 업그레이드를 할 수 있게 할것인가.
        boolean check = userService.upgradeAuth(username);

        return (check) ?
                ResponseEntity.status(HttpStatus.OK).body(null) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
}
