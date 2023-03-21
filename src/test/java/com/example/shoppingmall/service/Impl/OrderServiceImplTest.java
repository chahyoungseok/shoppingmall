package com.example.shoppingmall.service.Impl;

import com.example.shoppingmall.data.dto.queryselect.QueryOrderProduct;
import com.example.shoppingmall.data.dto.queryselect.ReadOrderQuery;
import com.example.shoppingmall.data.dto.request.RequestOrder;
import com.example.shoppingmall.data.dto.response.ResponseOrder;
import com.example.shoppingmall.data.entity.*;
import com.example.shoppingmall.repository.order.OrderProductRepository;
import com.example.shoppingmall.repository.order.OrderRepository;
import com.example.shoppingmall.repository.product.ProductRepository;
import com.example.shoppingmall.repository.user.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {
    @InjectMocks
    private OrderServiceImpl orderService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderProductRepository orderProductRepository;

    User param;

    Product product_1;
    Product product_2;

    Order order_1;
    Order order_2;

    OrderProduct orderProduct_1;
    OrderProduct orderProduct_2;
    OrderProduct orderProduct_3;

    List<ReadOrderQuery> readOrderQueryList_1 = new ArrayList<>();
    List<ReadOrderQuery> readOrderQueryList_2 = new ArrayList<>();

    List<List<ResponseOrder>> expected = new ArrayList<>();

    @Nested
    @DisplayName("주문목록_읽기")
    class read_order {

        @Test
        @DisplayName("주문목록_읽기_성공")
        @Transactional
        void 주문목록_읽기_성공(){
            // given
            makeAll();

            // mocking
            BDDMockito.given(userRepository.findByUsername(any()))
                    .willReturn(param);
            BDDMockito.given(orderProductRepository.findResponseOrder(order_1.getOrderProductList()))
                    .willReturn(readOrderQueryList_1);
            BDDMockito.given(orderProductRepository.findResponseOrder(order_2.getOrderProductList()))
                    .willReturn(readOrderQueryList_2);

            // when
            List<List<ResponseOrder>> result = orderService.read_order(param);

            // then
            Assertions.assertEquals(expected.toString(), result.toString());
        }

        @Test
        @DisplayName("주문목록_읽기_실패_유저의_주문이_없을때")
        @Transactional
        void 주문목록_읽기_실패_유저의_주문이_없을때(){
            // given
            makeUser();

            // setter가 없으므로 ReflectionTestUtils 사용
            ReflectionTestUtils.setField(param, "orderList", null);

            // when
            List<List<ResponseOrder>> result = orderService.read_order(param);

            // then
            Assertions.assertNull(result);
        }
    }

    @Nested
    @DisplayName("주문목록_쓰기")
    class create_order {

        RequestOrder requestOrder;

        List<List<ResponseOrder>> expected;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

        create_order(){
            // given
            requestOrder = new RequestOrder(
                    LocalDateTime.now().format(formatter),
                    "주문완료",
                    Arrays.asList(
                            new QueryOrderProduct(1L, 3, "S"),
                            new QueryOrderProduct(2L, 1, "M"))
            );

            expected = List.of(Arrays.asList(
                    new ResponseOrder(
                            1L,
                            LocalDateTime.parse(requestOrder.getOrder_date()),
                            requestOrder.getOrder_status(),
                            "리얼 와이드 히든 밴딩 슬랙스",
                            3, 43900, "S",
                            "https://mallimageupload.s3.ap-northeast-2.amazonaws.com/image/ba77a690a2b4-11ed-96f9-4f762f37fb59.jpeg",
                            false),

                    new ResponseOrder(
                            2L,
                            LocalDateTime.parse(requestOrder.getOrder_date()),
                            requestOrder.getOrder_status(),
                            "와이드 데님 팬츠 [라이트 인디고]",
                            1, 49900, "M",
                            "https://mallimageupload.s3.ap-northeast-2.amazonaws.com/image/c1574b50a2b4-11ed-96f9-4f762f37fb59.jpeg",
                            false)
            ));
        }

        @Test
        @DisplayName("주문목록_쓰기_성공")
        @Transactional
        void 주문목록_쓰기_성공(){
            // given
            makeUser();
            makeProduct();

            // mocking
            BDDMockito.given(userRepository.findByUsername(any()))
                    .willReturn(param);
            BDDMockito.given(orderRepository.save(any()))
                    .willReturn(requestOrder.toEntity());
            BDDMockito.given(productRepository.findByIdList(any()))
                    .willReturn(Arrays.asList(product_1, product_2));
            BDDMockito.given(orderProductRepository.findResponseOrder(any()))
                    .willReturn(Arrays.asList(
                            new ReadOrderQuery(1L, 3, 43900, "리얼 와이드 히든 밴딩 슬랙스", "S", "https://mallimageupload.s3.ap-northeast-2.amazonaws.com/image/ba77a690a2b4-11ed-96f9-4f762f37fb59.jpeg", false),
                            new ReadOrderQuery(2L, 1, 49900, "와이드 데님 팬츠 [라이트 인디고]", "M", "https://mallimageupload.s3.ap-northeast-2.amazonaws.com/image/c1574b50a2b4-11ed-96f9-4f762f37fb59.jpeg", false)
                    ));

            // when
            List<List<ResponseOrder>> result = orderService.create_order(param, requestOrder);

            // then
            Assertions.assertEquals(expected.toString(), result.toString());
        }

        @Test
        @DisplayName("주문목록_쓰기_성공_장바구니에_상품이없지만_구매버튼을_눌렀을때")
        @Transactional
        void 주문목록_쓰기_성공_장바구니에_상품이없지만_구매버튼을_눌렀을때(){
            // given
            ReflectionTestUtils.setField(requestOrder, "queryOrderProductList", new ArrayList<>());

            makeUser();

            // mocking
            BDDMockito.given(userRepository.findByUsername(any()))
                    .willReturn(param);

            // when
            List<List<ResponseOrder>> result = orderService.create_order(param, requestOrder);

            // then
            Assertions.assertNull(result);
        }
    }

    private void makeUser(){
        param = new User(
                null,
                "seokseok",
                "hs_good",
                "$2a$10$vG6NvQImCGrMOq78yQiLxuvBt/O7qF7LAH./w9h20WoXoau7EfDgq",
                "010-5161-6612",
                "hs2@gmail.com",
                "sangmyung university - 123",
                Authority.USER
        );
    }

    private void makeProduct(){
        product_1 = new Product(1L,"리얼 와이드 히든 밴딩 슬랙스",43900,"pants","멋진 바지1 입니다.","S,M,L","https://mallimageupload.s3.ap-northeast-2.amazonaws.com/image/ba77a690a2b4-11ed-96f9-4f762f37fb59.jpeg", LocalDateTime.now(),642,19, param,20);
        product_2 = new Product(2L,"와이드 데님 팬츠 [라이트 인디고]",49900,"pants","멋진 바지2 입니다.","S,M,L","https://mallimageupload.s3.ap-northeast-2.amazonaws.com/image/c1574b50a2b4-11ed-96f9-4f762f37fb59.jpeg", LocalDateTime.now(),76,22, param, 14);
    }

    private void makeOrder(){
        order_1 = new Order(1L, LocalDateTime.now(), "배송완료", param);
        order_2 = new Order(2L, LocalDateTime.now(), "배송완료", param);

        param.addOrder(order_1);
        param.addOrder(order_2);
    }

    private void makeOrderProduct(){
        orderProduct_1 = new OrderProduct(1L, 1, order_1, product_1, "S");
        orderProduct_2 = new OrderProduct(2L, 2, order_1, product_2, "M");
        orderProduct_3 = new OrderProduct(3L, 3, order_2, product_1, "M");

        order_1.addAllOrderProduct(Arrays.asList(orderProduct_1, orderProduct_2));
        order_2.addAllOrderProduct(List.of(orderProduct_3));
    }

    private void makeReadOrderQueryList(){
        readOrderQueryList_1 = Arrays.asList(
                new ReadOrderQuery(product_1.getId(), orderProduct_1.getCount(), product_1.getPrice(), product_1.getName(), orderProduct_1.getSize(), product_1.getImgKey(), false),
                new ReadOrderQuery(product_2.getId(), orderProduct_2.getCount(), product_2.getPrice(), product_2.getName(), orderProduct_2.getSize(), product_2.getImgKey(), false));

        readOrderQueryList_2 = List.of(
                new ReadOrderQuery(product_1.getId(), orderProduct_3.getCount(), product_1.getPrice(), product_1.getName(), orderProduct_3.getSize(), product_1.getImgKey(), false));
    }

    private void makeExpected(){
        expected = Arrays.asList(
                new ArrayList<>(readOrderQueryList_2
                        .stream().map(readOrderQuery -> ResponseOrder
                                .builder()
                                .order(order_2)
                                .readOrderQuery(readOrderQuery)
                                .build()).toList()),
                new ArrayList<>(readOrderQueryList_1
                        .stream().map(readOrderQuery -> ResponseOrder
                                .builder()
                                .order(order_1)
                                .readOrderQuery(readOrderQuery)
                                .build()).toList()));
    }

    private void makeAll(){
        makeUser();
        makeProduct();
        makeOrder();
        makeOrderProduct();
        makeReadOrderQueryList();
        makeExpected();
    }
}