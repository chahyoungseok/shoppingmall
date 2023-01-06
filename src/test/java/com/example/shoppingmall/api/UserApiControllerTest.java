package com.example.shoppingmall.api;

import com.example.shoppingmall.data.dto.request.RequestJoin;
import com.example.shoppingmall.data.dto.request.RequestUsername;
import com.example.shoppingmall.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// Mock이란 테스트를 위한 가짜 객체
// 실제 객체를 만들기에는 비용과 시간, 의존성이 크게 걸쳐져있어 테스트 시 제대로 구현하기 어려울 경우 만드는 가짜 객체입니다.
@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class UserApiControllerTest {

    @Mock
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    // Request와 Response에 직접적으로 연결되어있는 Controller에서 Request와 Response를 만들기 위해 사용.
    @Autowired
    private MockMvc mockMvc;

    @DisplayName("회원 가입 성공")
    @Transactional
    @Test
    void join_success() throws Exception{
        String content = objectMapper.writeValueAsString(new RequestJoin(
                "HyoungSeok",
                "qwer1234",
                "hs_good",
                "010-1234-5678",
                "hs@naver.com",
                "sangmyung university"));

        mockMvc.perform(post("/join")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andDo(print());
    }

    @DisplayName("아이디 중복 확인 성공")
    @Test
    void check_id_success() throws Exception{
        String content = objectMapper.writeValueAsString(new RequestUsername("HyoungSeok"));

        mockMvc.perform(post("/check_id")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andDo(print());
    }

    @DisplayName("아이디 중복 확인 실패")
    @Test
    void check_id_fail() throws Exception{
        String content = objectMapper.writeValueAsString(new RequestUsername("hwang"));

        mockMvc.perform(post("/check_id")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }
}