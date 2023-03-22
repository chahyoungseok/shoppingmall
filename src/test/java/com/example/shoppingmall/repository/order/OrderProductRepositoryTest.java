package com.example.shoppingmall.repository.order;

import com.example.shoppingmall.config.TestConfig;
import com.example.shoppingmall.data.dto.queryselect.ReadOrderQuery;
import com.example.shoppingmall.data.entity.*;
import com.example.shoppingmall.repository.product.ProductRepository;
import com.example.shoppingmall.repository.user.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@DataJpaTest
@Import(TestConfig.class)
class OrderProductRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderProductRepository orderProductRepository;

    User user;

    Product product_1;
    Product product_2;

    Order order_1;

    OrderProduct orderProduct_1;
    OrderProduct orderProduct_2;

    List<ReadOrderQuery> readOrderQueryList_1 = new ArrayList<>();

    @Nested
    @DisplayName("주문상품리스트_삭제")
    class findResponseOrder {

        @Test
        @DisplayName("주문상품리스트_삭제_성공")
        @Transactional
        void 주문상품리스트_삭제_성공(){
            // given
            makeAll();

            userRepository.save(user);
            productRepository.saveAll(Arrays.asList(product_1, product_2));
            orderRepository.save(order_1);
            orderProductRepository.saveAll(Arrays.asList(orderProduct_1, orderProduct_2));

            // when
            List<ReadOrderQuery> result = orderProductRepository.findResponseOrder(Arrays.asList(orderProduct_2, orderProduct_1));

            // then
            result.forEach(readOrderQuery -> readOrderQuery.setProduct_id(null));

            Assertions.assertEquals(readOrderQueryList_1.toString(), result.toString());
        }
    }

    private void makeUser(){
        user = new User(
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
        product_1 = new Product(null,"리얼 와이드 히든 밴딩 슬랙스",43900,"pants","멋진 바지1 입니다.","S,M,L","https://mallimageupload.s3.ap-northeast-2.amazonaws.com/image/ba77a690a2b4-11ed-96f9-4f762f37fb59.jpeg", LocalDateTime.now(),642,19, user,20);
        product_2 = new Product(null,"와이드 데님 팬츠 [라이트 인디고]",49900,"pants","멋진 바지2 입니다.","S,M,L","https://mallimageupload.s3.ap-northeast-2.amazonaws.com/image/c1574b50a2b4-11ed-96f9-4f762f37fb59.jpeg", LocalDateTime.now(),76,22, user, 14);
    }

    private void makeOrder(){
        order_1 = new Order(null, LocalDateTime.now(), "배송완료", user);

        user.addOrder(order_1);
    }

    private void makeOrderProduct(){
        orderProduct_1 = new OrderProduct(null, 1, order_1, product_1, "S");
        orderProduct_2 = new OrderProduct(null, 2, order_1, product_2, "M");

        order_1.addAllOrderProduct(Arrays.asList(orderProduct_1, orderProduct_2));
    }

    private void makeReadOrderQueryList(){
        readOrderQueryList_1 = Arrays.asList(
                new ReadOrderQuery(product_1.getId(), orderProduct_1.getCount(), product_1.getPrice(), product_1.getName(), orderProduct_1.getSize(), product_1.getImgKey(), true),
                new ReadOrderQuery(product_2.getId(), orderProduct_2.getCount(), product_2.getPrice(), product_2.getName(), orderProduct_2.getSize(), product_2.getImgKey(), true));
    }

    private void makeAll() {
        makeUser();
        makeProduct();
        makeOrder();
        makeOrderProduct();
        makeReadOrderQueryList();
    }
}