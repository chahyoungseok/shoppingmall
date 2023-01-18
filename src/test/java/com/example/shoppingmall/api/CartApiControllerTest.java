package com.example.shoppingmall.api;

import com.example.shoppingmall.config.auth.PrincipalDetails;
import com.example.shoppingmall.service.CartService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CartApiControllerTest extends BaseControllerTest{

    @Mock
    private CartService cartService;

    @Nested
    @DisplayName("카트 조회")
    @WithUserDetails(value = "hwang")
    class read_cart {

        @DisplayName("성공")
        @Transactional
        @Test
        void success() throws Exception{
            String url = "/user/cart";
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
    @DisplayName("카트 추가")
    @WithUserDetails(value = "ann")
    class createCart {

        @DisplayName("성공")
        @Transactional
        @Test
        void success() throws Exception{
            String url = "/user/create_cart/1";
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();

            RequestBuilder requestBuilder = MockMvcRequestBuilders
                    .post(url)
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
    @DisplayName("카트 삭제")
    @WithUserDetails(value = "hwang")
    class deleteCart {

        @DisplayName("성공")
        @Transactional
        @Test
        void success() throws Exception{
            String url = "/user/delete_cart/2";
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();

            RequestBuilder requestBuilder = MockMvcRequestBuilders
                    .delete(url)
                    .requestAttr("user", principalDetails.getUser());
            ResultActions resultActions = mockMvc
                    .perform(requestBuilder);
            // then
            resultActions
                    .andExpect(status().isOk())
                    .andDo(print());
        }

        /** Cart 테이블에 hwang이 가진 1번 상품이 없음*/
        @DisplayName("실패")
        @Transactional
        @Test
        void fail() throws Exception{
            String url = "/user/delete_cart/1";
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();

            RequestBuilder requestBuilder = MockMvcRequestBuilders
                    .delete(url)
                    .requestAttr("user", principalDetails.getUser());
            ResultActions resultActions = mockMvc
                    .perform(requestBuilder);
            // then
            resultActions
                    .andExpect(status().isBadRequest())
                    .andDo(print());
        }
    }
}