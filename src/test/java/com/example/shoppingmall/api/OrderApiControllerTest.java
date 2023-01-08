package com.example.shoppingmall.api;

import com.example.shoppingmall.data.dto.request.RequestModify;
import com.example.shoppingmall.data.dto.request.RequestOrder;
import com.example.shoppingmall.service.OrderService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class OrderApiControllerTest extends BaseControllerTest {
    @Mock
    private OrderService orderService;

    @Nested
    @DisplayName("주문 조회")
    @WithUserDetails(value = "hwang")
    class read_order {
        @Test
        @DisplayName("성공")
        @Transactional
        void success() throws Exception {
            String url = "/user/orderlist";

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            RequestBuilder requestBuilder = MockMvcRequestBuilders
                    .get(url)
                    .requestAttr("username", authentication.getName());
            ResultActions resultActions = mockMvc
                    .perform(requestBuilder);

            resultActions
                    .andExpect(status().isOk())
                    .andDo(print());
        }


        @Test
        @DisplayName("실패")
        @Transactional
        void fail() throws Exception {
            String url = "/user/orderlist";

            RequestBuilder requestBuilder = MockMvcRequestBuilders
                    .get(url)
                    .requestAttr("username", "HyoungSeok");
            ResultActions resultActions = mockMvc
                    .perform(requestBuilder);

            resultActions
                    .andExpect(status().isBadRequest())
                    .andDo(print());
        }
    }

    @Nested
    @DisplayName("주문 생성")
    @WithUserDetails(value = "hwang")
    class create_order {
        @Test
        @DisplayName("성공")
        @Transactional
        void success() throws Exception {
            String url = "/user/create_order";
            String content = objectMapper.writeValueAsString(new RequestOrder(
                    null,
                    LocalDateTime.now(),
                    "배송완료",
                    4,
                    1L));

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            RequestBuilder requestBuilder = MockMvcRequestBuilders
                    .post(url)
                    .requestAttr("username", authentication.getName())
                    .content(content)
                    .contentType(MediaType.APPLICATION_JSON);
            ResultActions resultActions = mockMvc
                    .perform(requestBuilder);

            resultActions
                    .andExpect(status().isOk())
                    .andDo(print());
        }
    }
}