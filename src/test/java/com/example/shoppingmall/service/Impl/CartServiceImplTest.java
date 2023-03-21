package com.example.shoppingmall.service.Impl;

import com.example.shoppingmall.data.dto.response.ResponseCart;
import com.example.shoppingmall.data.entity.Authority;
import com.example.shoppingmall.data.entity.Product;
import com.example.shoppingmall.data.entity.User;
import com.example.shoppingmall.repository.cart.CartRepository;
import com.example.shoppingmall.repository.product.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

@ExtendWith(MockitoExtension.class)
class CartServiceImplTest {

    @InjectMocks
    private CartServiceImpl cartService;

    @Mock
    private CartRepository cartRepository;

    @Mock
    private ProductRepository productRepository;

    User param;

    Product product_1;
    Product product_2;

    List<ResponseCart> responseCartList;

    @Nested
    @DisplayName("장바구니_읽기")
    class readCart {

        readCart() {
            // given
            makeUser();
            makeResponseCartList();
        }

        @Test
        @Transactional
        @DisplayName("장바구니_읽기_성공")
        void 장바구니_읽기_성공(){
            // mocking
            BDDMockito.given(cartRepository.findAllCart(any()))
                    .willReturn(responseCartList);

            // when
            List<ResponseCart> expected = cartService.readCart(param);

            // then
            Assertions.assertEquals(expected.toString(), responseCartList.toString());
        }

        @Test
        @Transactional
        @DisplayName("장바구니_읽기_실패_장바구니_상품이_없을때")
        void 장바구니_읽기_실패_장바구니_상품이_없을때(){
            // mocking
            BDDMockito.given(cartRepository.findAllCart(any()))
                    .willReturn(null);

            // when
            List<ResponseCart> expected = cartService.readCart(param);

            // then
            Assertions.assertNull(expected);
        }
    }

    @Nested
    @DisplayName("장바구니_추가")
    class createCart {
        createCart() {
            // given
            makeUser();
        }

        @Test
        @Transactional
        @DisplayName("장바구니_추가_성공_장바구니에_없는_상품일때")
        void 장바구니_추가_성공_장바구니에_없는_상품일때(){
            // given
            makeProduct();

            List<ResponseCart> result = List.of(new ResponseCart(
                    null,
                    "리얼 와이드 히든 밴딩 슬랙스",
                    43900,
                    "S",
                    "https://mallimageupload.s3.ap-northeast-2.amazonaws.com/image/ba77a690a2b4-11ed-96f9-4f762f37fb59.jpeg",
                    2,
                    false));

            // mocking
            BDDMockito.given(productRepository.findById(any()))
                    .willReturn(Optional.of(product_1));
            BDDMockito.given(cartRepository.findSameCart(any(), any(), any(), anyInt()))
                    .willReturn(false);
            BDDMockito.given(cartRepository.findAllCart(any()))
                    .willReturn(result);

            // when
            List<ResponseCart> expected = cartService.createCart(param, 1L, "S");

            // then
            Assertions.assertEquals(expected.toString(), result.toString());
        }

        @Test
        @Transactional
        @DisplayName("장바구니_추가_성공_장바구니에_있는_상품일때")
        void 장바구니_추가_성공_장바구니에_있는_상품일때(){
            // given
            makeProduct();

            List<ResponseCart> result = List.of(new ResponseCart(
                    null,
                    "리얼 와이드 히든 밴딩 슬랙스",
                    43900,
                    "S",
                    "https://mallimageupload.s3.ap-northeast-2.amazonaws.com/image/ba77a690a2b4-11ed-96f9-4f762f37fb59.jpeg",
                    4,
                    false));

            // mocking
            BDDMockito.given(productRepository.findById(any()))
                    .willReturn(Optional.of(product_1));
            BDDMockito.given(cartRepository.findSameCart(any(), any(), any(), anyInt()))
                    .willReturn(true);
            BDDMockito.given(cartRepository.findAllCart(any()))
                    .willReturn(result);

            // when
            List<ResponseCart> expected = cartService.createCart(param, 1L, "S");

            // then
            Assertions.assertEquals(expected.toString(), result.toString());
        }

        @Test
        @Transactional
        @DisplayName("장바구니_추가_실패_상품아이디가_잘못왔을때")
        void 장바구니_추가_실패_상품아이디가_잘못왔을때(){
            // mocking
            BDDMockito.given(productRepository.findById(any()))
                    .willReturn(Optional.empty());

            // when
            List<ResponseCart> expected = cartService.createCart(param, 2L, "S");

            // then
            Assertions.assertNull(expected);
        }
    }

    @Nested
    @DisplayName("장바구니_단일삭제")
    class deleteCart {
        deleteCart() {
            // given
            makeUser();
        }

        @Test
        @Transactional
        @DisplayName("장바구니_단일삭제_성공")
        void 장바구니_단일삭제_성공(){
            // given
            makeProduct();

            List<ResponseCart> result = List.of(new ResponseCart(
                    null,
                    "리얼 와이드 히든 밴딩 슬랙스",
                    43900,
                    "S",
                    "https://mallimageupload.s3.ap-northeast-2.amazonaws.com/image/ba77a690a2b4-11ed-96f9-4f762f37fb59.jpeg",
                    2,
                    false));

            // mocking
            BDDMockito.given(productRepository.findById(any()))
                    .willReturn(Optional.of(product_1));
            BDDMockito.given(cartRepository.findSameCart(any(), any(), any(), anyInt()))
                    .willReturn(true);
            BDDMockito.given(cartRepository.findAllCart(any()))
                    .willReturn(result);

            // when
            List<ResponseCart> expected = cartService.deleteCart(param, 1L, "S");

            // then
            Assertions.assertEquals(expected.toString(), result.toString());
        }

        @Test
        @Transactional
        @DisplayName("장바구니_단일삭제_실패_장바구니에_없는_상품의_삭제요청이_왔을때")
        void 장바구니_단일삭제_실패_장바구니에_없는_상품의_삭제요청이_왔을때(){
            // given
            makeProduct();

            // mocking
            BDDMockito.given(productRepository.findById(any()))
                    .willReturn(Optional.of(product_1));
            BDDMockito.given(cartRepository.findSameCart(any(), any(), any(), anyInt()))
                    .willReturn(false);

            // when
            List<ResponseCart> expected = cartService.deleteCart(param, 1L, "S");

            // then
            Assertions.assertNull(expected);
        }

        @Test
        @Transactional
        @DisplayName("장바구니_단일삭제_실패_상품아이디가_잘못왔을때")
        void 장바구니_단일삭제_실패_상품아이디가_잘못왔을때(){
            // mocking
            BDDMockito.given(productRepository.findById(any()))
                    .willReturn(Optional.empty());

            // when
            List<ResponseCart> expected = cartService.deleteCart(param, 2L, "S");

            // then
            Assertions.assertNull(expected);
        }
    }

    @Nested
    @DisplayName("장바구니_전체상품_전체삭제")
    class deleteCartList {

        @Test
        @Transactional
        @DisplayName("장바구니_전체상품_전체삭제_성공")
        void 장바구니_전체상품_전체삭제_성공(){
            // mocking
            BDDMockito.given(cartRepository.deleteCartList(any()))
                    .willReturn(1L);

            // when
            Boolean expected = cartService.deleteCartList(1L);

            // then
            Assertions.assertEquals(expected, true);
        }

        @Test
        @Transactional
        @DisplayName("장바구니_전체상품_전체삭제_실패_유저아이디가_잘못왔을때")
        void 장바구니_전체상품_전체삭제_실패_유저아이디가_잘못왔을때(){
            // mocking
            BDDMockito.given(cartRepository.deleteCartList(any()))
                    .willReturn(0L);

            // when
            Boolean expected = cartService.deleteCartList(1L);

            // then
            Assertions.assertEquals(expected, false);
        }
    }

    @Nested
    @DisplayName("장바구니_특정상품_전체삭제")
    class deleteAllCart {

        deleteAllCart() {
            // given
            makeUser();
        }

        @Test
        @Transactional
        @DisplayName("장바구니_특정상품_전체삭제_성공")
        void 장바구니_특정상품_전체삭제_성공(){
            // given
            makeProduct();

            List<ResponseCart> result = List.of(new ResponseCart(
                    null,
                    "리얼 와이드 히든 밴딩 슬랙스",
                    43900,
                    "S",
                    "https://mallimageupload.s3.ap-northeast-2.amazonaws.com/image/ba77a690a2b4-11ed-96f9-4f762f37fb59.jpeg",
                    2,
                    false));

            // mocking
            BDDMockito.given(productRepository.findById(any()))
                    .willReturn(Optional.of(product_1));
            BDDMockito.given(cartRepository.deleteAllCart(any(), any(), any()))
                    .willReturn(true);
            BDDMockito.given(cartRepository.findAllCart(any()))
                    .willReturn(result);

            // when
            List<ResponseCart> expected = cartService.deleteAllCart(param, 1L, "S");

            // then
            Assertions.assertEquals(expected.toString(), result.toString());
        }

        @Test
        @Transactional
        @DisplayName("장바구니_특정상품_전체삭제_실패_삭제쿼리가_실패했을때")
        void 장바구니_특정상품_전체삭제_실패_삭제쿼리가_실패했을때(){
            // given
            makeProduct();

            // mocking
            BDDMockito.given(productRepository.findById(any()))
                    .willReturn(Optional.of(product_1));
            BDDMockito.given(cartRepository.deleteAllCart(any(), any(), any()))
                    .willReturn(false);

            // when
            List<ResponseCart> expected = cartService.deleteAllCart(param, 1L, "S");

            // then
            Assertions.assertNull(expected);
        }

        @Test
        @Transactional
        @DisplayName("장바구니_특정상품_전체삭제_실패_상품아이디가_잘못왔을때")
        void 장바구니_특정상품_전체삭제_실패_상품아이디가_잘못왔을때(){
            // mocking
            BDDMockito.given(productRepository.findById(any()))
                    .willReturn(Optional.empty());

            // when
            List<ResponseCart> expected = cartService.deleteAllCart(param, 2L, "S");

            // then
            Assertions.assertNull(expected);
        }
    }

    private void makeUser() {
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

    private void makeResponseCartList(){
        responseCartList = Arrays.asList(
                new ResponseCart(
                        null,
                        "리얼 와이드 히든 밴딩 슬랙스",
                        43900,
                        "S",
                        "https://mallimageupload.s3.ap-northeast-2.amazonaws.com/image/ba77a690a2b4-11ed-96f9-4f762f37fb59.jpeg",
                        2,
                        false),

                new ResponseCart(
                        null,
                        "와이드 데님 팬츠 [라이트 인디고]",
                        49900,
                        "M",
                        "https://mallimageupload.s3.ap-northeast-2.amazonaws.com/image/c1574b50a2b4-11ed-96f9-4f762f37fb59.jpeg",
                        1,
                        false)
        );
    }
}