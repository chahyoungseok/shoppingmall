package com.example.shoppingmall.api;

import com.example.shoppingmall.aop.annotation.UserAnnotation;
import com.example.shoppingmall.data.dto.request.RequestChangePWD;
import com.example.shoppingmall.data.dto.request.RequestJoin;
import com.example.shoppingmall.data.dto.request.RequestModify;
import com.example.shoppingmall.data.dto.response.ResponseUser;
import com.example.shoppingmall.data.entity.Authority;
import com.example.shoppingmall.data.entity.User;
import com.example.shoppingmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserApiController {

    private final UserService userService;

    @Autowired
    public UserApiController(UserService userService) {
        this.userService = userService;
    }

    /** 회원가입 */
    @PostMapping("/join")
    public ResponseEntity<Void> join(@Valid @RequestBody RequestJoin requestJoin){
        boolean check = userService.create(requestJoin);

        return (check) ?
                ResponseEntity.status(HttpStatus.OK).body(null) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    /** 회원 정보 페이지 */
    @GetMapping("/user")
    public ResponseEntity<ResponseUser> my_page(@UserAnnotation User user){
        ResponseUser responseUser = ResponseUser.builder().user(user).build();

        return ResponseEntity.status(HttpStatus.OK).body(responseUser);
    }

    /** 회원 정보 수정 */
    @PutMapping("/user")
    public ResponseEntity<ResponseUser> update(@UserAnnotation User user, @Valid @RequestBody RequestModify requestModify) {
        ResponseUser responseUser = userService.updateUser(requestModify, user);
        return (responseUser != null) ?
                ResponseEntity.status(HttpStatus.OK).body(responseUser) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    /** 아이디 중복확인 */
    @GetMapping("/check_id/{username}")
    public ResponseEntity<Void> check_id(@PathVariable String username){
        boolean check = userService.checkUsername(username);
        return (!check) ?
                ResponseEntity.status(HttpStatus.OK).body(null) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    /** 전화번호 중복확인 */
    @GetMapping("/check_telephone/{telephone}")
    public ResponseEntity<Void> check_telephone(@PathVariable String telephone){
        boolean check = userService.checkTelephone(telephone);
        return (!check) ?
                ResponseEntity.status(HttpStatus.OK).body(null) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    /** 이메일 중복확인 */
    @GetMapping("/check_email/{email}")
    public ResponseEntity<Void> check_email(@PathVariable String email){
        boolean check = userService.checkEmail(email);
        return (!check) ?
                ResponseEntity.status(HttpStatus.OK).body(null) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    /** 비밀번호 변경 */
    @PostMapping("/user/pwd_change")
    public ResponseEntity<Void> change_pwd(@UserAnnotation User user, @Valid @RequestBody RequestChangePWD requestChangePWD){
        boolean check_pwd = userService.change_pwd(requestChangePWD, user);

        return (check_pwd) ?
                ResponseEntity.status(HttpStatus.OK).body(null) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    /** 회원 탈퇴 */
    @DeleteMapping("/user")
    public ResponseEntity<Void> delete(@UserAnnotation User user) {
        // 관리자는 일반적인 유저 삭제 Api 로 삭제 불가능
        if(user.getAuthority().equals(Authority.ADMIN)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        boolean check_delete = userService.deleteUser(user.getUsername());

        return (check_delete) ?
                ResponseEntity.status(HttpStatus.OK).body(null) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    /** 권한 업그레이드 */
    @PatchMapping("/admin/upgradeAuth/{username}")
    public ResponseEntity<Void> upgradeAuth(@PathVariable String username){
        // 한번에 많은 유저의 업그레이드를 할 수 있게 할것인가.
        boolean check = userService.upgradeAuth(username);

        return (check) ?
                ResponseEntity.status(HttpStatus.OK).body(null) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
}
