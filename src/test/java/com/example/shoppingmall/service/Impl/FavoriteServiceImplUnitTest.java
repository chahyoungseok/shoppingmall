package com.example.shoppingmall.service.Impl;

import com.example.shoppingmall.data.dto.response.ResponseFavorite;
import com.example.shoppingmall.data.entity.Authority;
import com.example.shoppingmall.data.entity.Favorite;
import com.example.shoppingmall.data.entity.Product;
import com.example.shoppingmall.data.entity.User;
import com.example.shoppingmall.repository.favorite.FavoriteRepository;
import com.example.shoppingmall.repository.product.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

class FavoriteServiceImplUnitTest extends BaseServiceImplUnitTest {

    @InjectMocks
    private FavoriteServiceImpl favoriteService;

    @Mock
    private FavoriteRepository favoriteRepository;

    @Mock
    private ProductRepository productRepository;

    public User param;

    Product product_1;
    Product product_2;

    List<ResponseFavorite> responseFavoriteList;

    @Nested
    @DisplayName("내_좋아요목록_읽기")
    class myFavorite {

        myFavorite() {
            // given
            makeUser();
        }

        @Test
        @Transactional
        @DisplayName("내_좋아요목록_읽기_성공")
        void 내_좋아요목록_읽기_성공(){
            // given
            makeResponseFavoriteList();

            // mocking
            BDDMockito.given(favoriteRepository.findAllFavorite(any()))
                    .willReturn(responseFavoriteList);

            // when
            List<ResponseFavorite> expected = favoriteService.myFavorite(param);

            // then
            Assertions.assertEquals(expected.toString(), responseFavoriteList.toString());
        }

        @Test
        @Transactional
        @DisplayName("내_좋아요목록_읽기_실패_좋아요가_하나도_없을때")
        void 내_좋아요목록_읽기_실패_좋아요가_하나도_없을때(){
            // mocking
            BDDMockito.given(favoriteRepository.findAllFavorite(any()))
                    .willReturn(null);

            // when
            List<ResponseFavorite> expected = favoriteService.myFavorite(param);

            // then
            Assertions.assertNull(expected);
        }
    }

    @Nested
    @DisplayName("좋아요_추가")
    class addFavorite {

        addFavorite() {
            // given
            makeUser();
            makeProduct();
        }

        @Test
        @Transactional
        @DisplayName("좋아요_추가_성공")
        void 좋아요_추가_성공(){
            // mocking
            BDDMockito.given(productRepository.findById(any()))
                    .willReturn(Optional.of(product_1));
            BDDMockito.given(favoriteRepository.findByUserIdAndProductId(any(), any()))
                    .willReturn(null);
            BDDMockito.given(favoriteRepository.save(any()))
                    .willReturn(new Favorite(null, param, product_1));

            // when
            Boolean expected = favoriteService.addFavorite(param, product_1.getId());

            // then
            Assertions.assertEquals(expected, true);
        }

        @Test
        @Transactional
        @DisplayName("좋아요_추가_실패_이미좋아요가_눌러져있을때")
        void 좋아요_추가_실패_이미좋아요가_눌러져있을때(){
            // mocking
            BDDMockito.given(productRepository.findById(any()))
                    .willReturn(Optional.of(product_1));
            BDDMockito.given(favoriteRepository.findByUserIdAndProductId(any(), any()))
                    .willReturn(new Favorite(null, param, product_1));

            // when
            Boolean expected = favoriteService.addFavorite(param, product_1.getId());

            // then
            Assertions.assertEquals(expected, false);
        }

        @Test
        @Transactional
        @DisplayName("좋아요_추가_실패_상품아이디가_잘못왔을때")
        void 좋아요_추가_실패_상품아이디가_잘못왔을때(){
            // mocking
            BDDMockito.given(productRepository.findById(any()))
                    .willReturn(Optional.empty());

            // when
            Boolean expected = favoriteService.addFavorite(param, product_1.getId());

            // then
            Assertions.assertEquals(expected, false);
        }
    }

    @Nested
    @DisplayName("좋아요_삭제")
    class deleteFavorite {

        deleteFavorite() {
            // given
            makeUser();
            makeProduct();
        }

        @Test
        @Transactional
        @DisplayName("좋아요_삭제_성공")
        void 좋아요_삭제_성공(){
            // mocking
            BDDMockito.given(favoriteRepository.findByUserIdAndProductId(any(), any()))
                    .willReturn(new Favorite(null, param, product_1));

            // when
            Boolean expected = favoriteService.deleteFavorite(param, product_1.getId());

            // then
            Assertions.assertEquals(expected, true);
        }

        @Test
        @Transactional
        @DisplayName("좋아요_삭제_실패_좋아요가_없을때")
        void 좋아요_삭제_실패_좋아요가_없을때(){
            // mocking
            BDDMockito.given(favoriteRepository.findByUserIdAndProductId(any(), any()))
                    .willReturn(null);

            // when
            Boolean expected = favoriteService.deleteFavorite(param, product_1.getId());

            // then
            Assertions.assertEquals(expected, false);
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

    private void makeResponseFavoriteList(){
        responseFavoriteList = Arrays.asList(
                new ResponseFavorite(
                        null,
                        "리얼 와이드 히든 밴딩 슬랙스",
                        43900,
                        19,
                        "https://mallimageupload.s3.ap-northeast-2.amazonaws.com/image/ba77a690a2b4-11ed-96f9-4f762f37fb59.jpeg",
                        false),

                new ResponseFavorite(
                        null,
                        "와이드 데님 팬츠 [라이트 인디고]",
                        49900,
                        22,
                        "https://mallimageupload.s3.ap-northeast-2.amazonaws.com/image/c1574b50a2b4-11ed-96f9-4f762f37fb59.jpeg",
                        false)
        );
    }
}