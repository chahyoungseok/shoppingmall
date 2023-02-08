package com.example.shoppingmall.api;

import com.example.shoppingmall.config.auth.PrincipalDetails;
import com.example.shoppingmall.data.dto.request.ChangeStockQuery;
import com.example.shoppingmall.data.dto.request.RequestProduct;
import com.example.shoppingmall.data.dto.request.RequestProductModify;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ProductApiControllerTest extends BaseControllerTest{

    @Test
    @DisplayName("메인 페이지")
    void mainPage() throws Exception {
        // given
        String url = "/";
        // when
        ResultActions resultActions = mockMvc
                .perform(get(url));
        // then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andDo(print());
    }

    @Nested
    @DisplayName("상품명으로 검색")
    class findByProductName{
        @Test
        @DisplayName("검색결과 O")
        void success() throws Exception {
            // given
            String url = "/shop/search/coat";
            // when
            ResultActions resultActions = mockMvc
                    .perform(get(url));
            // then
            resultActions
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$").exists())
                    .andDo(print());
        }

        @Test
        @DisplayName("검색결과 X")
        void fail() throws Exception {
            // given
            String url = "/shop/search/fail";
            // when
            ResultActions resultActions = mockMvc
                    .perform(get(url));
            // then
            resultActions
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$").doesNotExist())
                    .andDo(print());
        }
    }


    @Test
    @DisplayName("상품 전체 조회")
    void findAllProduct() throws Exception {
        // given
        String url = "/shop";
        // when
        ResultActions resultActions = mockMvc
                .perform(get(url));
        // then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andDo(print());
    }

    @Test
    @DisplayName("상품 카테고리별 조회")
    void findByCategory() throws Exception{
        // given
        String url = "/shop/category/outer";
        // when
        ResultActions resultActions = mockMvc
                .perform(get(url));
        // then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andDo(print());
    }

    @Test
    @DisplayName("상품 상세 페이지 조회")
    void findById() throws Exception{
        // given
        String url = "/shop/detail/1";
        // when
        ResultActions resultActions = mockMvc
                .perform(get(url));
        // then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andDo(print());
    }

    @Test
    @DisplayName("판매등록한 상품 목록 조회")
    @WithUserDetails(value = "hwang")
    void findByUsername() throws Exception{
        // given
        String url = "/register/product";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();

        // when
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

    @Test
    @DisplayName("상품 등록")
    @WithUserDetails(value = "hwang")
    void createProduct() throws Exception{
        // given
        String url = "/register/product";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();

        String content = objectMapper.writeValueAsString(new RequestProduct(
                "nike",
                3000,
                "shoes",
                "나이키 신발",
                "270,280,290",
                "A",
                LocalDateTime.now().withNano(0).toString()));

        // when
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(url)
                .requestAttr("user", principalDetails.getUser())
                .content(content)
                .contentType(MediaType.APPLICATION_JSON);
        ResultActions resultActions = mockMvc
                .perform(requestBuilder);
        // then
        resultActions
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("상품 정보 수정 페이지")
    @WithUserDetails(value = "hwang")
    void editProduct() throws Exception{
        // given
        String url = "/register/product/1";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();

        // when
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

    @Test
    @DisplayName("상품 정보 수정")
    @WithUserDetails(value = "hwang")
    void updateProduct() throws Exception{
        // given
        String url = "/register/product/1";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();

        String content = objectMapper.writeValueAsString(new RequestProductModify(
                null,
                null,
                "nike",
                3000,
                "shoes",
                "나이키 신발",
                "270",
                "A"));
        // when
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put(url)
                .requestAttr("user", principalDetails.getUser())
                .content(content)
                .contentType(MediaType.APPLICATION_JSON);

        ResultActions resultActions = mockMvc
                .perform(requestBuilder);
        // then
        resultActions
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Nested
    @DisplayName("상품 재고 추가")
    public class add_stock{
        @Test
        @WithUserDetails("jinjin")
        @DisplayName("성공")
        void success() throws Exception{
            // given
            String url = "/register/product/add_stock";
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();

            String content = objectMapper.writeValueAsString(new ChangeStockQuery(2L, 3));

            // when
            RequestBuilder requestBuilder = MockMvcRequestBuilders
                    .put(url)
                    .requestAttr("user", principalDetails.getUser())
                    .content(content)
                    .contentType(MediaType.APPLICATION_JSON);

            ResultActions resultActions = mockMvc
                    .perform(requestBuilder);
            // then
            resultActions
                    .andExpect(status().isOk())
                    .andDo(print());
        }

        @Test
        @WithUserDetails("jinjin")
        @DisplayName("실패")
        void fail() throws Exception{
            // given
            String url = "/register/product/add_stock";
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();

            // 실패의 경우는 내가 등록하지 않은 상품의 재고를 채우려할 때 발생한다.
            String content = objectMapper.writeValueAsString(new ChangeStockQuery(1L, 3));

            // when
            RequestBuilder requestBuilder = MockMvcRequestBuilders
                    .put(url)
                    .requestAttr("user", principalDetails.getUser())
                    .content(content)
                    .contentType(MediaType.APPLICATION_JSON);

            ResultActions resultActions = mockMvc
                    .perform(requestBuilder);
            // then
            resultActions
                    .andExpect(status().isBadRequest())
                    .andDo(print());
        }
    }


    @Test
    @DisplayName("상품 삭제")
    @WithUserDetails(value = "hwang")
    void deleteProduct() throws Exception{
        // given
        String url = "/register/product/1";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        // when
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
}