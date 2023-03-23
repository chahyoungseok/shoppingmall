package com.example.shoppingmall.repository.cart;

import com.example.shoppingmall.data.dto.response.ResponseCart;
import com.example.shoppingmall.data.entity.Authority;
import com.example.shoppingmall.data.entity.Cart;
import com.example.shoppingmall.data.entity.Product;
import com.example.shoppingmall.data.entity.User;
import com.example.shoppingmall.repository.base.BaseRepositoryUnitTest;
import com.example.shoppingmall.repository.product.ProductRepository;
import com.example.shoppingmall.repository.user.UserRepository;
import com.example.shoppingmall.service.Impl.CartServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

class CartRepositoryUnitTest extends BaseRepositoryUnitTest {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    private User user;

    private Product product_1;
    private Product product_2;

    @Nested
    @DisplayName("특정유저_장바구니_전체조회")
    class findAllCart {

        String param;

        findAllCart() {
            // given
            makeUser();
            makeProduct();

            param = user.getUsername();
        }

        @Test
        @Transactional
        @DisplayName("특정유저_장바구니_전체조회_성공")
        void 특정유저_장바구니_전체조회_성공(){
            // given
            userRepository.save(user);
            productRepository.saveAll(Arrays.asList(product_1, product_2));

            cartRepository.saveAll(Arrays.asList(
                    new Cart(null, 2, user, product_1, "S"),
                    new Cart(null, 1, user, product_2, "M")
            ));

            // when
            List<ResponseCart> result = cartRepository.findAllCart(param);

            // then
            Assertions.assertEquals(Arrays.asList(
                    new ResponseCart(product_1.getId(), product_1.getName(), product_1.getPrice(), "S", product_1.getImgKey(), 2, true),
                    new ResponseCart(product_2.getId(), product_2.getName(), product_2.getPrice(), "M", product_2.getImgKey(), 1, true)
                ).toString(),
            result.toString());
        }

        @Test
        @Transactional
        @DisplayName("특정유저_장바구니_전체조회_실패_장바구니에_담지않았을때")
        void 특정유저_장바구니_전체조회_실패_장바구니에_담지않았을때(){
            // when
            List<ResponseCart> result = cartRepository.findAllCart(param);

            // then
            Assertions.assertEquals(List.of().toString(), result.toString());
        }
    }

    @Nested
    @DisplayName("장바구니에_이미있는지_여부")
    class findSameCart {

        findSameCart() {
            makeUser();
            makeProduct();
        }

        @Test
        @Transactional
        @DisplayName("장바구니에_이미있는지_여부_성공_state가_ADD일때")
        void 장바구니에_이미있는지_여부_성공_state가_ADD일때(){
            // given
            userRepository.save(user);
            productRepository.save(product_1);

            cartRepository.save(new Cart(null, 1, user, product_1, "S"));

            // when
            Boolean result = cartRepository.findSameCart(user.getId(), product_1.getId(), "S", CartServiceImpl.ADD);

            // then
            Assertions.assertEquals(true, result);
        }

        @Test
        @Transactional
        @DisplayName("장바구니에_이미있는지_여부_성공_state가_DELETE이고_삭제된_재고가0이될때")
        void 장바구니에_이미있는지_여부_성공_state가_DELETE이고_삭제된_재고가0이될때(){
            // given
            userRepository.save(user);
            productRepository.save(product_1);

            cartRepository.save(new Cart(null, 1, user, product_1, "S"));

            // when
            Boolean result = cartRepository.findSameCart(user.getId(), product_1.getId(), "S", CartServiceImpl.DELETE);

            // then
            Assertions.assertEquals(true, result);
        }

        @Test
        @Transactional
        @DisplayName("장바구니에_이미있는지_여부_성공_state가_DELETE이고_삭제된_재고가0이되지않을때")
        void 장바구니에_이미있는지_여부_성공_state가_DELETE이고_삭제된_재고가0이되지않을때(){
            // given
            userRepository.save(user);
            productRepository.save(product_1);

            cartRepository.save(new Cart(null, 1, user, product_1, "S"));

            // when
            Boolean result = cartRepository.findSameCart(user.getId(), product_1.getId(), "S", CartServiceImpl.DELETE);

            // then
            Assertions.assertEquals(true, result);
        }

        @Test
        @Transactional
        @DisplayName("장바구니에_이미있는지_여부_실패_장바구니에_없는_상품일경우")
        void 장바구니에_이미있는지_여부_실패_장바구니에_없는_상품일경우(){
            // given
            userRepository.save(user);
            productRepository.save(product_1);

            // when
            Boolean result = cartRepository.findSameCart(user.getId(), product_1.getId(), "S", CartServiceImpl.DELETE);

            // then
            Assertions.assertEquals(false, result);
        }
    }

    @Nested
    @DisplayName("장바구니에_특정유저정보_전체삭제")
    class deleteCartList {

        deleteCartList() {
            makeUser();
            makeProduct();
        }

        @Test
        @Transactional
        @DisplayName("장바구니에_특정유저정보_전체삭제_성공")
        void 장바구니에_특정유저정보_전체삭제_성공(){
            // given
            userRepository.save(user);
            productRepository.save(product_1);

            cartRepository.save(new Cart(null, 1, user, product_1, "S"));

            // when
            Long result = cartRepository.deleteCartList(user.getId());

            // then
            Assertions.assertEquals(1L, result);
        }

        @Test
        @Transactional
        @DisplayName("장바구니에_특정유저정보_전체삭제_실패")
        void 장바구니에_특정유저정보_전체삭제_실패(){
            // given
            userRepository.save(user);

            // when
            Long result = cartRepository.deleteCartList(user.getId());

            // then
            Assertions.assertEquals(0L, result);
        }
    }

    @Nested
    @DisplayName("장바구니에_특정유저의_특정상품정보_전체삭제")
    class deleteAllCart {

        deleteAllCart() {
            makeUser();
            makeProduct();
        }

        @Test
        @Transactional
        @DisplayName("장바구니에_특정유저의_특정상품정보_전체삭제_성공")
        void 장바구니에_특정유저의_특정상품정보_전체삭제_성공(){
            // given
            userRepository.save(user);
            productRepository.save(product_1);

            cartRepository.save(new Cart(null, 3, user, product_1, "S"));

            // when
            Boolean result = cartRepository.deleteAllCart(user.getId(), product_1.getId(), "S");

            // then
            Assertions.assertEquals(true, result);
        }

        @Test
        @Transactional
        @DisplayName("장바구니에_특정유저의_특정상품정보_전체삭제_실패")
        void 장바구니에_특정유저의_특정상품정보_전체삭제_실패(){
            // given
            userRepository.save(user);
            productRepository.save(product_1);

            // when
            Boolean result = cartRepository.deleteAllCart(user.getId(), product_1.getId(), "S");

            // then
            Assertions.assertEquals(false, result);
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
}