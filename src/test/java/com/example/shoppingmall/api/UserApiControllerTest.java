package com.example.shoppingmall.api;

import com.example.shoppingmall.config.auth.PrincipalDetails;
import com.example.shoppingmall.data.dto.request.RequestChangePWD;
import com.example.shoppingmall.data.dto.request.RequestJoin;
import com.example.shoppingmall.data.dto.request.RequestModify;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// Mock이란 테스트를 위한 가짜 객체
// 실제 객체를 만들기에는 비용과 시간, 의존성이 크게 걸쳐져있어 테스트 시 제대로 구현하기 어려울 경우 만드는 가짜 객체입니다.
@ExtendWith(MockitoExtension.class)
class UserApiControllerTest extends BaseControllerTest{

    @Nested
    @DisplayName("회원가입")
    class join {
        @Test
        @DisplayName("성공")
        @Transactional
        void success() throws Exception{
            String content = objectMapper.writeValueAsString(new RequestJoin(
                    "HyoungSeok",
                    "qwer1234",
                    "hs_good",
                    "010-5161-6611",
                    "hs@naver.com",
                    "sangmyung university - 123"));

            mockMvc.perform(post("/join")
                            .content(content)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andDo(print());
        }

        // 비밀번호 7자이므로 회원가입 실패
        @Test
        @DisplayName("실패_비밀번호")
        @Transactional
        void fail_password() throws Exception{
            String content = objectMapper.writeValueAsString(new RequestJoin(
                    "HyoungSeok",
                    "qwer123",
                    "hs_good",
                    "010-1234-5678",
                    "hs@naver.com",
                    "sangmyung university"));

            mockMvc.perform(post("/join")
                            .content(content)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest())
                    .andDo(print());
        }

        // 이메일이 형식에 맞지않으므로 실패
        @DisplayName("실패_이메일")
        @Transactional
        @Test
        void fail_email() throws Exception{
            String content = objectMapper.writeValueAsString(new RequestJoin(
                    "HyoungSeok",
                    "qwer1234",
                    "hs_good",
                    "010-1234-5678",
                    "33naver.com",
                    "sangmyung university"));

            mockMvc.perform(post("/join")
                            .content(content)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest())
                    .andDo(print());
        }
    }

    @Nested
    @DisplayName("아이디 중복 확인")
    class check_id {
        @DisplayName("성공")
        @Test
        void success() throws Exception{
            String content = "HyoungSeok";

            mockMvc.perform(get("/check_id/" + content))
                    .andExpect(status().isOk())
                    .andDo(print());
        }

        @DisplayName("실패")
        @Test
        void fail() throws Exception{
            String content = "hwang";

            mockMvc.perform(get("/check_id/" + content)
                            .param("username", content))
                    .andExpect(status().isBadRequest())
                    .andDo(print());
        }
    }

    @Nested
    @DisplayName("내 정보 페이지")
    class my_page {
        @WithUserDetails(value = "hwang")
        @DisplayName("성공")
        @Test
        void success() throws Exception{
            String url = "/user/mypage";

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();

            RequestBuilder requestBuilder = MockMvcRequestBuilders
                    .get(url)
                    .requestAttr("user", principalDetails.getUser());
            ResultActions resultActions = mockMvc
                    .perform(requestBuilder);
            // then
            resultActions
                    .andExpect(status().isOk())
                    .andDo(print());
        }
    }

    @Nested
    @DisplayName("유저 업데이트")
    @WithUserDetails(value = "hwang")
    class update {
        @DisplayName("성공")
        @Test
        void success() throws Exception{
            String url = "/user/update";
            String content = objectMapper.writeValueAsString(new RequestModify(
                    "hwang",
                    "hs_good",
                    "010-1234-5678",
                    "hs@naver.com",
                    "sangmyung university"));

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();

            RequestBuilder requestBuilder = MockMvcRequestBuilders
                    .put(url)
                    .requestAttr("user", principalDetails.getUser())
                    .content(content)
                    .contentType(MediaType.APPLICATION_JSON);
            ResultActions resultActions = mockMvc
                    .perform(requestBuilder);

            resultActions
                    .andExpect(status().isOk())
                    .andDo(print());
        }
    }

    @Nested
    @DisplayName("비밀번호 변경")
    @WithUserDetails(value = "hwang")
    class pwd_change {
        @DisplayName("성공")
        @Test
        void success() throws Exception{
            String url = "/user/pwd_change";

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();

            String content = objectMapper.writeValueAsString(new RequestChangePWD(
                    "qwer1234",
                    "rewq4321"));

            RequestBuilder requestBuilder = MockMvcRequestBuilders
                    .post(url)
                    .requestAttr("user", principalDetails.getUser())
                    .content(content)
                    .contentType(MediaType.APPLICATION_JSON);
            ResultActions resultActions = mockMvc
                    .perform(requestBuilder);

            resultActions
                    .andExpect(status().isOk())
                    .andDo(print());
        }

        @DisplayName("실패")
        @Test
        void fail() throws Exception{
            String url = "/user/pwd_change";

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();

            String content = objectMapper.writeValueAsString(new RequestChangePWD(
                    "qwer1233",
                    "rewq4321"));

            RequestBuilder requestBuilder = MockMvcRequestBuilders
                    .post(url)
                    .requestAttr("user", principalDetails.getUser())
                    .content(content)
                    .contentType(MediaType.APPLICATION_JSON);
            ResultActions resultActions = mockMvc
                    .perform(requestBuilder);

            resultActions
                    .andExpect(status().isBadRequest())
                    .andDo(print());
        }
    }

    @Nested
    @DisplayName("유저 삭제")
    @WithUserDetails(value = "hwang")
    class delete_user {
        @DisplayName("성공")
        @Test
        void success() throws Exception{
            String url = "/user/delete";

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();

            RequestBuilder requestBuilder = MockMvcRequestBuilders
                    .delete(url)
                    .requestAttr("user", principalDetails.getUser());
            ResultActions resultActions = mockMvc
                    .perform(requestBuilder);

            resultActions
                    .andExpect(status().isOk())
                    .andDo(print());
        }
    }

    @Nested
    @DisplayName("유저 권한 업그레이드")
    @WithUserDetails(value = "hwang")
    class upgradeAuth {
        @DisplayName("성공")
        @Test
        void success() throws Exception{
            String url = "/admin/upgradeAuth/ann";

            RequestBuilder requestBuilder = MockMvcRequestBuilders
                    .patch(url);
            ResultActions resultActions = mockMvc
                    .perform(requestBuilder);

            resultActions
                    .andExpect(status().isOk())
                    .andDo(print());
        }

        @DisplayName("실패")
        @Test
        void fail() throws Exception{
            String url = "/admin/upgradeAuth/ann_dd";


            RequestBuilder requestBuilder = MockMvcRequestBuilders
                    .patch(url);
            ResultActions resultActions = mockMvc
                    .perform(requestBuilder);

            resultActions
                    .andExpect(status().isBadRequest())
                    .andDo(print());
        }
    }
}