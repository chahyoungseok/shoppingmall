package com.example.shoppingmall.service.Impl;

import com.example.shoppingmall.data.dto.request.RequestChangePWD;
import com.example.shoppingmall.data.dto.request.RequestModify;
import com.example.shoppingmall.data.dto.response.ResponseUser;
import com.example.shoppingmall.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @DisplayName("유저 업데이트 성공")
    @Transactional
    @Test
    void updateUser_success() {
        RequestModify requestModify = new RequestModify(
                "hwang",
                "hs_good",
                "010-1234-5678",
                "hs@naver.com",
                "sangmyung university");

        ResponseUser responseUser = userService.updateUser(requestModify);

        ResponseUser expected = new ResponseUser(
                "hwang",
                "hs_good",
                "010-1234-5678",
                "hs@naver.com",
                "sangmyung university");

        assertEquals(expected.toString(), responseUser.toString());
    }

    @DisplayName("유저 업데이트 실패")
    @Transactional
    @Test
    // 정보수정한 User의 username이 DB에 없을 때
    void updateUser_fail() {
        RequestModify requestModify = new RequestModify(
                "HyoungSeok",
                "hs_good",
                "010-1234-5678",
                "hs@naver.com",
                "sangmyung university");

        ResponseUser responseUser = userService.updateUser(requestModify);
        ResponseUser expected = null;
        assertEquals(expected, responseUser);
    }

    @DisplayName("유저 삭제 성공")
    @Transactional
    @Test
    void deleteUser_success() {
        String username = "hwang";

        boolean result = userService.deleteUser(username);

        boolean expected = true;

        assertEquals(expected, result);
    }

    @DisplayName("비밀번호 변경 성공")
    @Transactional
    @Test
    void change_pwd_success() {
        RequestChangePWD requestChangePWD = new RequestChangePWD(
                "hwang",
                "qwer1234",
                "rewq4321");

        boolean result = userService.change_pwd(requestChangePWD);
        boolean expected = true;
        assertEquals(expected, result);
    }

    @DisplayName("비밀번호 변경 실패")
    @Transactional
    @Test
    void change_pwd_fail() {
        RequestChangePWD requestChangePWD = new RequestChangePWD(
                "hwang",
                "qwer1233",
                "rewq4321");

        boolean result = userService.change_pwd(requestChangePWD);
        boolean expected = false;
        assertEquals(expected, result);
    }

    @DisplayName("권한 업그레이드 성공")
    @Transactional
    @Test
    void upgradeAuth() {
        String username = "hwang";

        ResponseUser result = userService.upgradeAuth(username);
        ResponseUser expected = new ResponseUser(
                "hwang",
                "admin",
                "010-4233-8110",
                  "weihyuk39@naver.com",
                 "sangmyung university");
        assertEquals(expected.toString(), result.toString());
    }
}