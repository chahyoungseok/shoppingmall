package com.example.shoppingmall.repository.product;

import com.example.shoppingmall.config.TestConfig;
import com.example.shoppingmall.data.dto.queryselect.SelectIDQuery;
import com.example.shoppingmall.data.dto.queryselect.SelectProductStockQuery;
import com.example.shoppingmall.data.dto.request.ChangeStockQuery;
import com.example.shoppingmall.data.dto.response.ResponseProductPurchase;
import com.example.shoppingmall.data.entity.*;
import com.example.shoppingmall.repository.order.OrderProductRepository;
import com.example.shoppingmall.repository.order.OrderRepository;
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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@DataJpaTest
@Import(TestConfig.class)
class ProductRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderProductRepository orderProductRepository;

    private User user;

    private Product product_1;
    private Product product_2;
    private Product product_3;
    private Product product_4;
    private Product product_5;
    private Product product_6;
    private Product product_7;
    private Product product_8;
    private Product product_9;
    private Product product_10;

    private List<Product> productList;

    private Order order_1;
    private Order order_2;

    private OrderProduct orderProduct_1;
    private OrderProduct orderProduct_2;
    private OrderProduct orderProduct_3;

    @Nested
    @DisplayName("메인페이지_상품조회_좋아요정렬_8개")
    class findTop8ByStockGreaterThanOrderByFavoriteDesc {

        findTop8ByStockGreaterThanOrderByFavoriteDesc() {
            makeUser();
            makeProduct();
            productList = Arrays.asList(product_1, product_2, product_3, product_4, product_5, product_6, product_7, product_8, product_9, product_10);
        }

        @Test
        @Transactional
        @DisplayName("메인페이지_상품조회_좋아요정렬_8개_성공")
        void 메인페이지_상품조회_좋아요정렬_8개_성공(){
            // given
            userRepository.save(user);
            productRepository.saveAll(productList);

            // when
            List<Product> result = productRepository.findTop8ByStockGreaterThanOrderByFavoriteDesc(0);

            // then
            Assertions.assertEquals(Arrays.asList(
                        product_3, product_10, product_4, product_6, product_9, product_2, product_1, product_5
                    ).toString(),
                    result.toString());
        }
    }

    @Nested
    @DisplayName("상품명으로_검색_조회수높은순_조회")
    class findByNameContainingAndStockGreaterThanOrderByHitsDesc {

        findByNameContainingAndStockGreaterThanOrderByHitsDesc() {
            makeUser();
            makeProduct();
            productList = Arrays.asList(product_1, product_2, product_3, product_4, product_5, product_6, product_7, product_8, product_9, product_10);
        }

        @Test
        @Transactional
        @DisplayName("상품명으로_검색_조회수높은순_조회_성공")
        void 상품명으로_검색_조회수높은순_조회_성공(){
            // given
            userRepository.save(user);
            productRepository.saveAll(productList);

            // when
            List<Product> result = productRepository.findByNameContainingAndStockGreaterThanOrderByHitsDesc("니트", 0);

            // then
            Assertions.assertEquals(Arrays.asList(product_9, product_8, product_10).toString(), result.toString());
        }
    }

    @Nested
    @DisplayName("상품명으로_검색_최신순_조회")
    class findByNameContainingAndStockGreaterThanOrderByDateDesc {

        findByNameContainingAndStockGreaterThanOrderByDateDesc() {
            makeUser();
            makeProduct();
            productList = Arrays.asList(product_1, product_2, product_3, product_4, product_5, product_6, product_7, product_8, product_9, product_10);
        }

        @Test
        @Transactional
        @DisplayName("상품명으로_검색_최신순_조회_성공")
        void 상품명으로_검색_조회수높은순_조회_성공(){
            // given
            userRepository.save(user);
            productRepository.saveAll(productList);

            // when
            List<Product> result = productRepository.findByNameContainingAndStockGreaterThanOrderByHitsDesc("니트", 0);

            // then
            Assertions.assertEquals(Arrays.asList(product_9, product_8, product_10).toString(), result.toString());
        }
    }

    @Nested
    @DisplayName("상품명으로_검색_좋아요높은순_조회")
    class findByNameContainingAndStockGreaterThanOrderByFavoriteDesc {

        findByNameContainingAndStockGreaterThanOrderByFavoriteDesc() {
            makeUser();
            makeProduct();
            productList = Arrays.asList(product_1, product_2, product_3, product_4, product_5, product_6, product_7, product_8, product_9, product_10);
        }

        @Test
        @Transactional
        @DisplayName("상품명으로_검색_좋아요높은순_조회_성공")
        void 상품명으로_검색_좋아요높은순_조회_성공(){
            // given
            userRepository.save(user);
            productRepository.saveAll(productList);

            // when
            List<Product> result = productRepository.findByNameContainingAndStockGreaterThanOrderByFavoriteDesc("니트", 0);

            // then
            Assertions.assertEquals(Arrays.asList(product_10, product_9, product_8).toString(), result.toString());
        }
    }

    @Nested
    @DisplayName("상품_전체_조회수높은순_조회")
    class findByStockGreaterThanOrderByHitsDesc {

        findByStockGreaterThanOrderByHitsDesc() {
            makeUser();
            makeProduct();
            productList = Arrays.asList(product_1, product_2, product_3, product_4, product_5, product_6, product_7, product_8, product_9, product_10);
        }

        @Test
        @Transactional
        @DisplayName("상품_전체_조회수높은순_조회_성공")
        void 상품_전체_조회수높은순_조회_성공(){
            // given
            userRepository.save(user);
            productRepository.saveAll(productList);

            // when
            List<Product> result = productRepository.findByStockGreaterThanOrderByHitsDesc(0);

            // then
            Assertions.assertEquals(Arrays.asList(
                    product_1, product_5, product_6, product_9, product_3, product_2, product_4, product_8, product_10, product_7
            ).toString(), result.toString());
        }
    }

    @Nested
    @DisplayName("상품_전체_최신순_조회")
    class findByStockGreaterThanOrderByDateDesc {

        findByStockGreaterThanOrderByDateDesc() {
            makeUser();
            makeProduct();
            productList = Arrays.asList(product_1, product_2, product_3, product_4, product_5, product_6, product_7, product_8, product_9, product_10);
        }

        @Test
        @Transactional
        @DisplayName("상품_전체_최신순_조회_성공")
        void 상품_전체_최신순_조회_성공(){
            // given
            userRepository.save(user);
            productRepository.saveAll(productList);

            // when
            List<Product> result = productRepository.findByStockGreaterThanOrderByDateDesc(0);

            // then
            Assertions.assertEquals(Arrays.asList(
                    product_1, product_2, product_3, product_4, product_5, product_6, product_7, product_8, product_9, product_10
            ).toString(), result.toString());
        }
    }

    @Nested
    @DisplayName("상품_전체_좋아요높은순_조회")
    class findByStockGreaterThanOrderByFavoriteDesc {

        findByStockGreaterThanOrderByFavoriteDesc() {
            makeUser();
            makeProduct();
            productList = Arrays.asList(product_1, product_2, product_3, product_4, product_5, product_6, product_7, product_8, product_9, product_10);
        }

        @Test
        @Transactional
        @DisplayName("상품_전체_좋아요높은순_조회_성공")
        void 상품_전체_좋아요높은순_조회_성공(){
            // given
            userRepository.save(user);
            productRepository.saveAll(productList);

            // when
            List<Product> result = productRepository.findByStockGreaterThanOrderByFavoriteDesc(0);

            // then
            Assertions.assertEquals(Arrays.asList(
                    product_3, product_10, product_4, product_6, product_9, product_2, product_1, product_5, product_7, product_8
            ).toString(), result.toString());
        }
    }

    @Nested
    @DisplayName("상품_카테고리별_조회수높은순_조회")
    class findByCategoryAndStockGreaterThanOrderByHitsDesc {

        findByCategoryAndStockGreaterThanOrderByHitsDesc() {
            makeUser();
            makeProduct();
            productList = Arrays.asList(product_1, product_2, product_3, product_4, product_5, product_6, product_7, product_8, product_9, product_10);
        }

        @Test
        @Transactional
        @DisplayName("상품_카테고리별_조회수높은순_조회_성공")
        void 상품_카테고리별_조회수높은순_조회_성공(){
            // given
            userRepository.save(user);
            productRepository.saveAll(productList);

            // when
            List<Product> result = productRepository.findByCategoryAndStockGreaterThanOrderByHitsDesc("top", 0);

            // then
            Assertions.assertEquals(Arrays.asList(
                    product_9, product_8, product_10, product_7
            ).toString(), result.toString());
        }
    }

    @Nested
    @DisplayName("상품_카테고리별_최신순_조회")
    class findByCategoryAndStockGreaterThanOrderByDateDesc {

        findByCategoryAndStockGreaterThanOrderByDateDesc() {
            makeUser();
            makeProduct();
            productList = Arrays.asList(product_1, product_2, product_3, product_4, product_5, product_6, product_7, product_8, product_9, product_10);
        }

        @Test
        @Transactional
        @DisplayName("상품_카테고리별_최신순_조회_성공")
        void 상품_카테고리별_최신순_조회_성공(){
            // given
            userRepository.save(user);
            productRepository.saveAll(productList);

            // when
            List<Product> result = productRepository.findByCategoryAndStockGreaterThanOrderByDateDesc("top", 0);

            // then
            Assertions.assertEquals(Arrays.asList(
                    product_7, product_8, product_9, product_10
            ).toString(), result.toString());
        }
    }

    @Nested
    @DisplayName("상품_카테고리별_좋아요높은순_조회")
    class findByCategoryAndStockGreaterThanOrderByFavoriteDesc {

        findByCategoryAndStockGreaterThanOrderByFavoriteDesc() {
            makeUser();
            makeProduct();
            productList = Arrays.asList(product_1, product_2, product_3, product_4, product_5, product_6, product_7, product_8, product_9, product_10);
        }

        @Test
        @Transactional
        @DisplayName("상품_카테고리별_좋아요높은순_조회_성공")
        void 상품_카테고리별_좋아요높은순_조회_성공(){
            // given
            userRepository.save(user);
            productRepository.saveAll(productList);

            // when
            List<Product> result = productRepository.findByCategoryAndStockGreaterThanOrderByFavoriteDesc("top", 0);

            // then
            Assertions.assertEquals(Arrays.asList(
                    product_10, product_9, product_7, product_8
            ).toString(), result.toString());
        }
    }

    @Nested
    @DisplayName("판매등록한_상품목록_조회")
    class findByUserId {

        findByUserId() {
            makeUser();
            makeProduct();
            productList = Arrays.asList(product_1, product_2, product_3, product_4, product_5, product_6, product_7, product_8, product_9, product_10);
        }

        @Test
        @Transactional
        @DisplayName("판매등록한_상품목록_조회_성공")
        void 판매등록한_상품목록_조회_성공(){
            // given
            userRepository.save(user);
            productRepository.saveAll(productList);

            // when
            List<Product> result = productRepository.findByUserId(user.getId());

            // then
            Assertions.assertEquals(Arrays.asList(
                    product_1, product_2, product_3, product_4, product_5, product_6, product_7, product_8, product_9, product_10
            ).toString(), result.toString());
        }

        @Test
        @Transactional
        @DisplayName("판매등록한_상품목록_조회_실패_등록한상품이_없을때")
        void 판매등록한_상품목록_조회_실패_등록한상품이_없을때(){
            // given
            userRepository.save(user);

            // when
            List<Product> result = productRepository.findByUserId(user.getId());

            // then
            Assertions.assertEquals(List.of().toString(), result.toString());
        }
    }

    @Nested
    @DisplayName("조회수_1증가")
    class increaseHits {

        increaseHits() {
            makeUser();
            makeProduct();
        }

        @Test
        @Transactional
        @DisplayName("조회수_1증가_성공")
        void 조회수_1증가_성공(){
            // given
            userRepository.save(user);
            productRepository.save(product_1);

            // when
            productRepository.increaseHits(product_1.getId());

            // then
            Assertions.assertEquals(643, productRepository.findById(product_1.getId()).get().getHits());
        }
    }

    @Nested
    @DisplayName("좋아요수_1증가")
    class increaseFavorite {

        increaseFavorite() {
            makeUser();
            makeProduct();
        }

        @Test
        @Transactional
        @DisplayName("좋아요수_1증가_성공")
        void 좋아요수_1증가_성공(){
            // given
            userRepository.save(user);
            productRepository.save(product_1);

            // when
            productRepository.increaseFavorite(product_1.getId());

            // then
            Assertions.assertEquals(20, productRepository.findById(product_1.getId()).get().getFavorite());
        }
    }

    @Nested
    @DisplayName("좋아요수_1감소")
    class decreaseFavorite {

        decreaseFavorite() {
            makeUser();
            makeProduct();
        }

        @Test
        @Transactional
        @DisplayName("좋아요수_1감소_성공")
        void 좋아요수_1감소_성공(){
            // given
            userRepository.save(user);
            productRepository.save(product_1);

            // when
            productRepository.decreaseFavorite(product_1.getId());

            // then
            Assertions.assertEquals(18, productRepository.findById(product_1.getId()).get().getFavorite());
        }
    }

    @Nested
    @DisplayName("유저가_등록한_상품아이디_반환")
    class selectIDFromUsername {

        selectIDFromUsername() {
            makeUser();
            makeProduct();
            productList = Arrays.asList(product_1, product_2, product_3, product_4, product_5, product_6, product_7, product_8, product_9, product_10);
        }

        @Test
        @Transactional
        @DisplayName("유저가_등록한_상품아이디_반환_성공")
        void 유저가_등록한_상품아이디_반환_성공(){
            // given
            userRepository.save(user);
            productRepository.saveAll(productList);

            // when
            List<SelectIDQuery> result = productRepository.selectIDFromUsername(user.getUsername());

            // then
            Assertions.assertEquals(
                    productList.stream().map(product -> new SelectIDQuery(product.getId())).toList().toString(),
                    result.toString());
        }

        @Test
        @Transactional
        @DisplayName("유저가_등록한_상품아이디_반환_실패_등록한_상품이없을때")
        void 유저가_등록한_상품아이디_반환_실패_등록한_상품이없을때(){
            // given
            userRepository.save(user);

            // when
            List<SelectIDQuery> result = productRepository.selectIDFromUsername(user.getUsername());

            // then
            Assertions.assertEquals(List.of().toString(), result.toString());
        }
    }

    @Nested
    @DisplayName("상품다수_읽기")
    class findByIdList {

        findByIdList() {
            makeUser();
            makeProduct();
            productList = Arrays.asList(product_1, product_2, product_3, product_4, product_5, product_6, product_7, product_8, product_9, product_10);
        }

        @Test
        @Transactional
        @DisplayName("상품다수_읽기_성공")
        void 상품다수_읽기_성공(){
            // given
            userRepository.save(user);
            productRepository.saveAll(productList);

            // when
            List<Product> result = productRepository.findByIdList(productRepository.findAll().stream().map(Product::getId).toList());

            // then
            Assertions.assertEquals(Arrays.asList(
                    product_1, product_2, product_3, product_4, product_5, product_6, product_7, product_8, product_9, product_10).toString(),
                    result.toString());
        }
    }

    @Nested
    @DisplayName("재고다수_변경")
    class updateProductListStock {

        updateProductListStock() {
            makeUser();
            makeProduct();
            productList = Arrays.asList(product_1, product_2, product_3, product_4, product_5, product_6, product_7, product_8, product_9, product_10);
        }

        @Test
        @Transactional
        @DisplayName("재고다수_변경_성공")
        void 재고다수_변경_성공(){
            // given
            userRepository.save(user);
            productRepository.saveAll(productList);

            List<Long> productIDList = productRepository.findAll().stream().map(Product::getId).toList();
            HashMap<Long, Integer> given = new HashMap<>();
            for(int i=0;i<productIDList.size();i++){
                given.put(productIDList.get(i), 1);
            }

            // when
            Integer result = productRepository.updateProductListStock(given);

            // then
            Assertions.assertEquals(1, result);
        }

        @Test
        @Transactional
        @DisplayName("재고다수_변경_실패_없는상품을_변경시키려할때")
        void 재고다수_변경_실패_없는상품을_변경시키려할때(){
            // given
            userRepository.save(user);

            HashMap<Long, Integer> given = new HashMap<>();
            given.put(-1L, 1);

            // when
            Integer result = productRepository.updateProductListStock(given);

            // then
            Assertions.assertEquals(0, result);
        }
    }

    @Nested
    @DisplayName("상품과재고를_반환")
    class findRemoveByProductIDList {

        findRemoveByProductIDList() {
            makeUser();
            makeProduct();
            productList = Arrays.asList(product_1, product_2, product_3, product_4, product_5, product_6, product_7, product_8, product_9, product_10);
        }

        @Test
        @Transactional
        @DisplayName("상품과재고를_반환_성공")
        void 상품과재고를_반환_성공(){
            // given
            userRepository.save(user);
            productRepository.saveAll(productList);

            List<Long> productIDList = productRepository.findAll().stream().map(Product::getId).toList();
            List<Product> given = productRepository.findByIdList(productIDList);

            // when
            List<ChangeStockQuery> result = productRepository.findRemoveByProductIDList(productIDList);

            // then
            Assertions.assertEquals(given.stream().map(product -> new ChangeStockQuery(product.getId(), product.getStock())).toList().toString(), result.toString());
        }
    }

    @Nested
    @DisplayName("상품과재고와_등록자를_반환")
    class findAddStockByProductID {

        findAddStockByProductID() {
            makeUser();
            makeProduct();
        }

        @Test
        @Transactional
        @DisplayName("상품과재고와_등록자를_반환_성공")
        void 상품과재고와_등록자를_반환_성공(){
            // given
            userRepository.save(user);
            productRepository.save(product_1);
            Product given = productRepository.findByFirstProduct();

            // when
            SelectProductStockQuery result = productRepository.findAddStockByProductID(given.getId());

            // then
            Assertions.assertEquals(new SelectProductStockQuery(given.getId(), 20, userRepository.findByUsername(user.getUsername()).getId()).toString(),
                    result.toString());
        }
    }

    @Nested
    @DisplayName("상품의_재고를_변경")
    class updateProductStock {

        updateProductStock() {
            makeUser();
            makeProduct();
        }

        @Test
        @Transactional
        @DisplayName("상품의_재고를_변경_성공")
        void 상품의_재고를_변경_성공(){
            // given
            userRepository.save(user);
            productRepository.save(product_1);
            Product given = productRepository.findByFirstProduct();

            // when
            int result = productRepository.updateProductStock(given.getId(), 4);

            // then
            Assertions.assertEquals(1, result);
        }

        @Test
        @Transactional
        @DisplayName("상품의_재고를_변경_실패_없는상품의_재고를변경")
        void 상품의_재고를_변경_실패_없는상품의_재고를변경(){
            // given
            userRepository.save(user);
            productRepository.save(product_1);
            List<Product> given = productRepository.findAll();

            // when
            int result = productRepository.updateProductStock(-1L, 4);

            // then
            Assertions.assertEquals(0, result);
        }
    }

    @Nested
    @DisplayName("상품_전체_구매수높은순_조회")
    class findAllProductPurchase {

        findAllProductPurchase() {
            makeUser();
            makeProduct();
            makeOrder();
            makeOrderProduct();
            productList = Arrays.asList(product_1, product_2);
        }

        @Test
        @Transactional
        @DisplayName("상품_전체_구매수높은순_조회_성공")
        void 상품_전체_구매수높은순_조회_성공(){
            // given
            userRepository.save(user);
            productRepository.saveAll(productList);
            orderRepository.saveAll(Arrays.asList(order_1, order_2));
            orderProductRepository.saveAll(Arrays.asList(orderProduct_1, orderProduct_2, orderProduct_3));

            List<Product> given = productRepository.findAll();

            // when
            List<ResponseProductPurchase> result = productRepository.findAllProductPurchase();

            // then
            Assertions.assertEquals(Arrays.asList(
                    new ResponseProductPurchase(given.get(0).getId(), product_1.getName(), product_1.getPrice(), product_1.getFavorite(), product_1.getImgKey(), 4),
                    new ResponseProductPurchase(given.get(1).getId(), product_2.getName(), product_2.getPrice(), product_2.getFavorite(), product_2.getImgKey(), 2)
            ).toString(), result.toString());
        }
    }

    @Nested
    @DisplayName("상품명으로_검색_구매순_조회")
    class findSearchProductPurchase {

        findSearchProductPurchase() {
            makeUser();
            makeProduct();
            makeOrder();
            makeOrderProduct();
            productList = Arrays.asList(product_1, product_2);
        }

        @Test
        @Transactional
        @DisplayName("상품명으로_검색_구매순_조회_성공")
        void 상품명으로_검색_구매순_조회_성공(){
            // given
            userRepository.save(user);
            productRepository.saveAll(productList);
            orderRepository.saveAll(Arrays.asList(order_1, order_2));
            orderProductRepository.saveAll(Arrays.asList(orderProduct_1, orderProduct_2, orderProduct_3));

            List<Product> given = productRepository.findAll();

            // when
            List<ResponseProductPurchase> result = productRepository.findSearchProductPurchase("와이드");

            // then
            Assertions.assertEquals(Arrays.asList(
                    new ResponseProductPurchase(given.get(0).getId(), product_1.getName(), product_1.getPrice(), product_1.getFavorite(), product_1.getImgKey(), 4),
                    new ResponseProductPurchase(given.get(1).getId(), product_2.getName(), product_2.getPrice(), product_2.getFavorite(), product_2.getImgKey(), 2)
            ).toString(), result.toString());
        }
    }

    @Nested
    @DisplayName("상품_카테고리별_구매수높은순_조회")
    class findCategoryProductPurchase {

        findCategoryProductPurchase() {
            makeUser();
            makeProduct();
            makeOrder();
            makeOrderProduct();
            productList = Arrays.asList(product_1, product_2);
        }

        @Test
        @Transactional
        @DisplayName("상품_카테고리별_구매수높은순_조회_성공")
        void 상품_카테고리별_구매수높은순_조회_성공(){
            // given
            userRepository.save(user);
            productRepository.saveAll(productList);
            orderRepository.saveAll(Arrays.asList(order_1, order_2));
            orderProductRepository.saveAll(Arrays.asList(orderProduct_1, orderProduct_2, orderProduct_3));

            List<Product> given = productRepository.findAll();

            // when
            List<ResponseProductPurchase> result = productRepository.findCategoryProductPurchase("pants");

            // then
            Assertions.assertEquals(Arrays.asList(
                    new ResponseProductPurchase(given.get(0).getId(), product_1.getName(), product_1.getPrice(), product_1.getFavorite(), product_1.getImgKey(), 4),
                    new ResponseProductPurchase(given.get(1).getId(), product_2.getName(), product_2.getPrice(), product_2.getFavorite(), product_2.getImgKey(), 2)
            ).toString(), result.toString());
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
        product_3 = new Product(null,"모헤어 가디건 [텐저린]",109900,"outer","브라운 계열의 가디건","S,M,L","https://mallimageupload.s3.ap-northeast-2.amazonaws.com/image/f1cf0940a2ae-11ed-bad0-9d4f844a9fa0.jpeg",LocalDateTime.now(),89,78,user, 11);
        product_4 = new Product(null,"모헤어 가디건 [레오파드]",119900,"outer","호피 무늬의 가디건","S,M,L","https://mallimageupload.s3.ap-northeast-2.amazonaws.com/image/f6c8aff0a2ae-11ed-bad0-9d4f844a9fa0.jpeg",LocalDateTime.now(),75,50,user, 16);
        product_5 = new Product(null,"리얼 와이드 히든 밴딩 슬랙스",43900,"pants","멋진 바지1 입니다.","S,M,L","https://mallimageupload.s3.ap-northeast-2.amazonaws.com/image/ba77a690a2b4-11ed-96f9-4f762f37fb59.jpeg",LocalDateTime.now(),641,18,user, 20);
        product_6 = new Product(null,"몬스터 다운 파카",239000,"outer","구스다운 형식의 롱패딩","S,M,L","https://mallimageupload.s3.ap-northeast-2.amazonaws.com/image/01a1f080a2af-11ed-bad0-9d4f844a9fa0.jpeg",LocalDateTime.now(),279,36,user, 20);
        product_7 = new Product(null,"오버사이즈 칼라드 스웨트셔츠 [피스타치오]",43900,"top","멋진 상의1 입니다.","S,M,L","https://mallimageupload.s3.ap-northeast-2.amazonaws.com/image/ce1662e0a2b4-11ed-96f9-4f762f37fb59.jpeg",LocalDateTime.now(),46,16,user, 40);
        product_8 = new Product(null,"메리노 울 블렌드 하이게이지 칼라드 니트",53900,"top","멋진 상의2 입니다.","S,M,L","https://mallimageupload.s3.ap-northeast-2.amazonaws.com/image/d11ac940a2b4-11ed-96f9-4f762f37fb59.jpeg",LocalDateTime.now(),74,11,user, 50);
        product_9 = new Product(null,"미니멀 크루 넥 니트",69900,"top","멋진 상의3 입니다.","S,M,L","https://mallimageupload.s3.ap-northeast-2.amazonaws.com/image/d6242760a2b4-11ed-96f9-4f762f37fb59.jpeg",LocalDateTime.now(),127,34,user, 60);
        product_10 = new Product(null,"쉐기 독 크루 넥 니트",79900,"top","멋진 상의4 입니다.","S,M,L","https://mallimageupload.s3.ap-northeast-2.amazonaws.com/image/e21c4570a2b4-11ed-96f9-4f762f37fb59.jpeg",LocalDateTime.now(),56,52,user, 20);
    }

    private void makeOrder(){
        order_1 = new Order(null, LocalDateTime.now(), "배송완료", user);
        order_2 = new Order(null, LocalDateTime.now(), "배송완료", user);

        user.addOrder(order_1);
        user.addOrder(order_2);
    }

    private void makeOrderProduct(){
        orderProduct_1 = new OrderProduct(null, 1, order_1, product_1, "S");
        orderProduct_2 = new OrderProduct(null, 2, order_1, product_2, "M");
        orderProduct_3 = new OrderProduct(null, 3, order_2, product_1, "M");

        order_1.addAllOrderProduct(Arrays.asList(orderProduct_1, orderProduct_2));
        order_2.addAllOrderProduct(List.of(orderProduct_3));
    }
}