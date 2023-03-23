package com.example.shoppingmall.repository.favorite;

import com.example.shoppingmall.data.dto.response.ResponseFavorite;
import com.example.shoppingmall.data.entity.Authority;
import com.example.shoppingmall.data.entity.Favorite;
import com.example.shoppingmall.data.entity.Product;
import com.example.shoppingmall.data.entity.User;
import com.example.shoppingmall.repository.base.BaseRepositoryUnitTest;
import com.example.shoppingmall.repository.product.ProductRepository;
import com.example.shoppingmall.repository.user.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

class FavoriteRepositoryUnitTest extends BaseRepositoryUnitTest {
    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    private User user;
    private Product product_1;
    private Product product_2;

    @Nested
    @DisplayName("좋아요_정보_읽기")
    class findByUserIdAndProductId {

        findByUserIdAndProductId() {
            // given
            makeUser();
            makeProduct();
        }

        @Test
        @Transactional
        @DisplayName("좋아요_정보_읽기_성공")
        void 좋아요_정보_읽기_성공(){
            // given
            userRepository.save(user);
            productRepository.save(product_1);

            favoriteRepository.save(new Favorite(null, user, product_1));

            // when
            Favorite result = favoriteRepository.findByUserIdAndProductId(user.getId(), product_1.getId());

            // then
            Assertions.assertNotNull(result);
        }
    }

    @Nested
    @DisplayName("유저의_좋아요_모두읽기")
    class findAllFavorite {

        findAllFavorite() {
            // given
            makeUser();
            makeProduct();
        }

        @Test
        @Transactional
        @DisplayName("유저의_좋아요_모두읽기_성공")
        void 유저의_좋아요_모두읽기_성공(){
            // given
            userRepository.save(user);
            productRepository.saveAll(Arrays.asList(product_1, product_2));

            favoriteRepository.saveAll(Arrays.asList(
                    new Favorite(null, user, product_1),
                    new Favorite(null, user, product_2)
            ));

            // when
            List<ResponseFavorite> result = favoriteRepository.findAllFavorite(user.getUsername());

            // then
            Assertions.assertEquals(Arrays.asList(
                            new ResponseFavorite(product_1.getId(), product_1.getName(), product_1.getPrice(), product_1.getFavorite(), product_1.getImgKey(), true),
                            new ResponseFavorite(product_2.getId(), product_2.getName(), product_2.getPrice(), product_2.getFavorite(), product_2.getImgKey(), true)
                        ).toString()
                    , result.toString());
        }

        @Test
        @Transactional
        @DisplayName("유저의_좋아요_모두읽기_실패_좋아요한_상품이없을때")
        void 유저의_좋아요_모두읽기_실패_좋아요한_상품이없을때(){
            // given
            userRepository.save(user);

            // when
            List<ResponseFavorite> result = favoriteRepository.findAllFavorite(user.getUsername());

            // then
            Assertions.assertEquals(List.of().toString(), result.toString());
        }
    }

    @Nested
    @DisplayName("유저의_좋아요_존재여부")
    class existUserProductByFavorite {

        existUserProductByFavorite() {
            // given
            makeUser();
            makeProduct();
        }

        @Test
        @Transactional
        @DisplayName("유저의_좋아요_존재여부_성공_존재할때")
        void 유저의_좋아요_존재여부_성공_존재할때(){
            // given
            userRepository.save(user);
            productRepository.save(product_1);

            favoriteRepository.save(new Favorite(null, user, product_1));

            // when
            Boolean result = favoriteRepository.existUserProductByFavorite(user.getId(), product_1.getId());

            // then
            Assertions.assertEquals(true, result);
        }

        @Test
        @Transactional
        @DisplayName("유저의_좋아요_존재여부_성공_존재하지않을때")
        void 유저의_좋아요_존재여부_성공_존재하지않을때(){
            // given
            userRepository.save(user);
            productRepository.save(product_1);

            // when
            Boolean result = favoriteRepository.existUserProductByFavorite(user.getId(), product_1.getId());

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