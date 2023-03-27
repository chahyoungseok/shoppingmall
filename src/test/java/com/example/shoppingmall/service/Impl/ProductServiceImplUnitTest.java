package com.example.shoppingmall.service.Impl;

import com.example.shoppingmall.data.dto.queryselect.QueryOrderProduct;
import com.example.shoppingmall.data.dto.queryselect.SelectProductStockQuery;
import com.example.shoppingmall.data.dto.request.ChangeStockQuery;
import com.example.shoppingmall.data.dto.request.RequestOrder;
import com.example.shoppingmall.data.dto.request.RequestProduct;
import com.example.shoppingmall.data.dto.request.RequestProductModify;
import com.example.shoppingmall.data.dto.response.ResponseBanner;
import com.example.shoppingmall.data.dto.response.ResponseProduct;
import com.example.shoppingmall.data.dto.response.ResponseProductDetails;
import com.example.shoppingmall.data.dto.response.ResponseProductSummary;
import com.example.shoppingmall.data.entity.Authority;
import com.example.shoppingmall.data.entity.Banner;
import com.example.shoppingmall.data.entity.Product;
import com.example.shoppingmall.data.entity.User;
import com.example.shoppingmall.repository.banner.BannerRepository;
import com.example.shoppingmall.repository.favorite.FavoriteRepository;
import com.example.shoppingmall.repository.product.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.util.ReflectionTestUtils;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

class ProductServiceImplUnitTest extends BaseServiceImplUnitTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private BannerRepository bannerRepository;

    @Mock
    private FavoriteRepository favoriteRepository;

    User param;

    Product product_1;
    Product product_2;
    Product product_3;
    Product product_4;

    List<Banner> bannerList;

    @Nested
    @DisplayName("메인페이지")
    class mainPageProductList {

        @Test
        @DisplayName("메인페이지_성공")
        @Transactional
        void 메인페이지_성공(){
            // given
            makeProduct();
            makeBanner();

            // mocking
            BDDMockito.given(productRepository.findTop8ByStockGreaterThanOrderByFavoriteDesc(0))
                    .willReturn(Arrays.asList(product_4, product_3, product_2, product_1));
            BDDMockito.given(bannerRepository.findAll())
                    .willReturn(bannerList);

            // when
            List<List<?>> result = productService.mainPageProductList();

            // then
            Assertions.assertEquals(Arrays.asList(
                    Arrays.asList(
                            ResponseProductSummary.builder().product(product_4).build(),
                            ResponseProductSummary.builder().product(product_3).build(),
                            ResponseProductSummary.builder().product(product_2).build(),
                            ResponseProductSummary.builder().product(product_1).build()),
                            bannerList.stream().map(banner -> ResponseBanner.builder().banner(banner).build()).toList()).toString(),
                    result.toString());
        }

        @Test
        @DisplayName("메인페이지_실패_상품이없을때")
        @Transactional
        void 메인페이지_실패_상품이없을때(){
            // given
            makeBanner();

            // mocking
            BDDMockito.given(productRepository.findTop8ByStockGreaterThanOrderByFavoriteDesc(0))
                    .willReturn(List.of());
            BDDMockito.given(bannerRepository.findAll())
                    .willReturn(bannerList);

            // when
            List<List<?>> result = productService.mainPageProductList();

            // then
            Assertions.assertEquals(List.of().toString(), result.toString());
        }

        @Test
        @DisplayName("메인페이지_실패_베너가없을때")
        @Transactional
        void 메인페이지_실패_베너가없을때(){
            // given
            makeProduct();

            // mocking
            BDDMockito.given(productRepository.findTop8ByStockGreaterThanOrderByFavoriteDesc(0))
                    .willReturn(Arrays.asList(product_4, product_3, product_2, product_1));
            BDDMockito.given(bannerRepository.findAll())
                    .willReturn(List.of());

            // when
            List<List<?>> result = productService.mainPageProductList();

            // then
            Assertions.assertEquals(List.of().toString(), result.toString());
        }
    }

    @Nested
    @DisplayName("상품상세_읽기")
    class findById {

        findById() {
            // given
            makeUser();
            makeProduct();
        }

        @Test
        @DisplayName("상품상세_읽기_성공")
        @Transactional
        void 상품상세_읽기_성공(){
            // mocking
            BDDMockito.given(productRepository.findById(any()))
                    .willReturn(Optional.of(product_1));
            BDDMockito.given(favoriteRepository.existUserProductByFavorite(any(), any()))
                    .willReturn(true);

            // when
            ResponseProductDetails result = productService.findById(param, product_1.getId());

            // then
            Assertions.assertEquals(ResponseProductDetails.builder().product(product_1).status(true).build().toString(),
                    result.toString());
        }

        @Test
        @DisplayName("상품상세_읽기_실패_토큰으로_온_정보가_null일때")
        @Transactional
        void 상품상세_읽기_실패_토큰으로_온_정보가_null일때(){
            // mocking
            BDDMockito.given(productRepository.findById(any()))
                    .willReturn(Optional.of(product_1));

            // when
            ResponseProductDetails result = productService.findById(null, product_1.getId());

            // then
            Assertions.assertEquals(ResponseProductDetails.builder().product(product_1).status(false).build().toString(),
                    result.toString());
        }

        @Test
        @DisplayName("상품상세_읽기_실패_요청_온_상품이_존재하지않을때")
        @Transactional
        void 상품상세_읽기_실패_요청_온_상품이_존재하지않을때(){
            // mocking
            BDDMockito.given(productRepository.findById(any()))
                    .willReturn(Optional.empty());

            // when
            ResponseProductDetails result = productService.findById(param, product_2.getId());

            // then
            Assertions.assertNull(result);
        }
    }

    @Nested
    @DisplayName("판매등록한_상품읽기")
    class findProductByUsername {

        findProductByUsername() {
            // given
            makeUser();
        }

        @Test
        @DisplayName("판매등록한_상품읽기_성공")
        @Transactional
        void 판매등록한_상품읽기_성공(){
            // given
            makeProduct();

            // mocking
            BDDMockito.given(productRepository.findByUserId(any()))
                    .willReturn(Arrays.asList(product_1, product_2, product_3, product_4));

            // when
            List<ResponseProductSummary> result = productService.findProductByUsername(param.getId());

            // then
            Assertions.assertEquals(
                    Arrays.asList(
                            ResponseProductSummary.builder().product(product_1).build(),
                            ResponseProductSummary.builder().product(product_2).build(),
                            ResponseProductSummary.builder().product(product_3).build(),
                            ResponseProductSummary.builder().product(product_4).build()
                    ).toString(),
                    result.toString());
        }

        @Test
        @DisplayName("판매등록한_상품읽기_실패")
        @Transactional
        void 판매등록한_상품읽기_실패_판매등록한_상품이_없을때(){
            // mocking
            BDDMockito.given(productRepository.findByUserId(any()))
                    .willReturn(List.of());

            // when
            List<ResponseProductSummary> result = productService.findProductByUsername(param.getId());

            // then
            Assertions.assertEquals(
                    List.of().toString(), result.toString());
        }
    }

    @Nested
    @DisplayName("상품등록")
    class createProduct {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

        RequestProduct requestProduct;

        createProduct() {
            requestProduct = new RequestProduct(
                    "리얼 와이드 히든 밴딩 슬랙스",
                    43900,
                    "pants",
                    "멋진 바지1 입니다.",
                    "S,M,L",
                    "https://mallimageupload.s3.ap-northeast-2.amazonaws.com/image/ba77a690a2b4-11ed-96f9-4f762f37fb59.jpeg",
                    LocalDateTime.now().format(formatter)
            );
        }

        @Test
        @DisplayName("상품등록_성공")
        @Transactional
        void 상품등록_성공(){
            // given
            makeUser();
            makeProduct();

            // mocking
            BDDMockito.given(productRepository.save(any()))
                    .willReturn(requestProduct.toEntity(param));

            // when
            Boolean result = productService.createProduct(requestProduct, param);

            // then
            Assertions.assertEquals(true, result);
        }
    }

    @Nested
    @DisplayName("상품수정_페이지_읽기")
    class editProduct {

        editProduct() {
            // given
            makeUser();
            makeProduct();
        }

        @Test
        @DisplayName("상품수정_페이지_읽기_성공")
        @Transactional
        void 상품수정_페이지_읽기_성공(){
            // mocking
            BDDMockito.given(productRepository.findById(any()))
                    .willReturn(Optional.of(product_1));

            // when
            ResponseProduct result = productService.editProduct(product_1.getId(), param);

            // then
            Assertions.assertEquals(
                    ResponseProduct.builder().product(product_1).build().toString(),
                    result.toString());
        }

        @Test
        @DisplayName("상품수정_페이지_읽기_실패_요청_온_상품이_존재하지않을때")
        @Transactional
        void 상품수정_페이지_읽기_실패_요청_온_상품이_존재하지않을때(){
            // mocking
            BDDMockito.given(productRepository.findById(any()))
                    .willReturn(Optional.empty());

            // when
            ResponseProduct result = productService.editProduct(product_2.getId(), param);

            // then
            Assertions.assertNull(result);
        }

        @Test
        @DisplayName("상품수정_페이지_읽기_실패_수정하려는_상품의_등록자가_아닐때")
        @Transactional
        void 상품수정_페이지_읽기_실패_수정하려는_상품의_등록자가_아닐때(){
            User other = makeOther();

            // mocking
            BDDMockito.given(productRepository.findById(any()))
                    .willReturn(Optional.of(product_1));

            // when
            ResponseProduct result = productService.editProduct(product_2.getId(), other);

            // then
            Assertions.assertNull(result);
        }
    }

    @Nested
    @DisplayName("상품수정")
    class updateProduct {

        RequestProductModify requestProductModify;

        updateProduct() {
            // given
            makeUser();

            requestProductModify = new RequestProductModify(
                    1L,
                    param.getUsername(),
                    "리얼 와이드 히든 밴딩 슬랙스",
                    53900,
                    "pants",
                    "멋진 바지1 입니다.",
                    "S,M,L",
                    "https://mallimageupload.s3.ap-northeast-2.amazonaws.com/image/ba77a690a2b4-11ed-96f9-4f762f37fb59.jpeg"
            );
        }

        @Test
        @DisplayName("상품수정_성공")
        @Transactional
        void 상품수정_성공(){
            // given
            makeProduct();

            // mocking
            BDDMockito.given(productRepository.findById(any()))
                    .willReturn(Optional.of(product_1));

            requestProductModify.toEntity(product_1);
            BDDMockito.given(productRepository.save(any()))
                    .willReturn(product_1);

            // when
            ResponseProduct result = productService.updateProduct(requestProductModify);

            // then
            Assertions.assertEquals(
                    ResponseProduct.builder().product(product_1).build().toString(),
                    result.toString());
        }

        @Test
        @DisplayName("상품수정_실패_요청_온_상품이_존재하지않을때")
        @Transactional
        void 상품수정_실패_요청_온_상품이_존재하지않을때(){
            // mocking
            BDDMockito.given(productRepository.findById(any()))
                    .willReturn(Optional.empty());

            // when
            ResponseProduct result = productService.updateProduct(requestProductModify);

            // then
            Assertions.assertNull(result);
        }

        @Test
        @DisplayName("상품수정_실패_요청_온_상품이_존재하지않을때")
        @Transactional
        void 상품수정_실패_수정하려는_상품의_등록자가_아닐때(){
            // given
            makeProduct();
            ReflectionTestUtils.setField(requestProductModify, "username","hwang");

            // mocking
            BDDMockito.given(productRepository.findById(any()))
                    .willReturn(Optional.of(product_1));

            // when
            ResponseProduct result = productService.updateProduct(requestProductModify);

            // then
            Assertions.assertNull(result);
        }
    }

    @Nested
    @DisplayName("상품삭제")
    class deleteProduct {

        deleteProduct() {
            // given
            makeUser();
            makeProduct();
        }

        @Test
        @DisplayName("상품삭제_성공")
        @Transactional
        void 상품삭제_성공(){

            // mocking
            BDDMockito.given(productRepository.findById(any()))
                    .willReturn(Optional.of(product_1));

            // when
            Boolean result = productService.deleteProduct(product_1.getId(), param);

            // then
            Assertions.assertEquals(true, result);
        }

        @Test
        @DisplayName("상품삭제_실패_요청_온_상품이_존재하지않을때")
        @Transactional
        void 상품삭제_실패_요청_온_상품이_존재하지않을때(){
            // mocking
            BDDMockito.given(productRepository.findById(any()))
                    .willReturn(Optional.empty());

            // when
            Boolean result = productService.deleteProduct(product_2.getId(), param);

            // then
            Assertions.assertEquals(false, result);
        }

        @Test
        @DisplayName("상품삭제_실패_삭제하려는_상품의_등록자가_아닐때")
        @Transactional
        void 상품삭제_실패_삭제하려는_상품의_등록자가_아닐때(){
            // given
            User other = makeOther();

            // mocking
            BDDMockito.given(productRepository.findById(any()))
                    .willReturn(Optional.of(product_1));

            // when
            Boolean result = productService.deleteProduct(product_1.getId(), other);

            // then
            Assertions.assertEquals(false, result);
        }
    }

    @Nested
    @DisplayName("상품구매")
    class purchaseProduct {

        RequestOrder requestOrder;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

        purchaseProduct() {
            requestOrder = new RequestOrder(
                    LocalDateTime.now().format(formatter),
                    "주문완료",
                    Arrays.asList(
                            new QueryOrderProduct(1L, 1, "S"),
                            new QueryOrderProduct(2L, 2, "M")
                    )
            );
        }

        @Test
        @DisplayName("상품구매_성공")
        @Transactional
        void 상품구매_성공(){
            // given
            makeUser();
            makeProduct();

            // mocking
            BDDMockito.given(productRepository.findRemoveByProductIDList(any()))
                    .willReturn(Arrays.asList(
                            new ChangeStockQuery(1L, 40),
                            new ChangeStockQuery(2L, 50)
                    ));
            BDDMockito.given(productRepository.updateProductListStock(any()))
                    .willReturn(1);

            // when
            Boolean result = productService.purchaseProduct(requestOrder);

            // then
            Assertions.assertEquals(true, result);
        }

        @Test
        @DisplayName("상품구매_실패_요청_온_상품이_존재하지않을때")
        @Transactional
        void 상품구매_실패_요청_온_상품이_존재하지않을때(){
            // mocking
            BDDMockito.given(productRepository.findRemoveByProductIDList(any()))
                    .willReturn(null);

            // when
            Boolean result = productService.purchaseProduct(requestOrder);

            // then
            Assertions.assertNull(result);
        }

        @Test
        @DisplayName("상품구매_실패_재고보다_많은양을_구매했을때")
        @Transactional
        void 상품구매_실패_재고보다_많은양을_구매했을때(){
            // given
            makeUser();
            makeProduct();

            // mocking
            BDDMockito.given(productRepository.findRemoveByProductIDList(any()))
                    .willReturn(Arrays.asList(
                            new ChangeStockQuery(1L, 40),
                            new ChangeStockQuery(2L, 1)
                    ));

            // when
            Boolean result = productService.purchaseProduct(requestOrder);

            // then
            Assertions.assertEquals(false, result);
        }
    }

    @Nested
    @DisplayName("재고추가")
    class stockUpProduct {

        ChangeStockQuery changeStockQuery;

        stockUpProduct() {
            // given
            changeStockQuery = new ChangeStockQuery(1L, 1);

            makeUser();
        }

        @Test
        @DisplayName("재고추가_성공")
        @Transactional
        void 재고추가_성공(){
            // given
            makeProduct();

            // mocking
            BDDMockito.given(productRepository.findAddStockByProductID(any()))
                    .willReturn(new SelectProductStockQuery(1L, 1, 1L));
            BDDMockito.given(productRepository.updateProductStock(any(), anyInt()))
                    .willReturn(1);

            // when
            Boolean result = productService.stockUpProduct(param, changeStockQuery);

            // then
            Assertions.assertEquals(true, result);
        }

        @Test
        @DisplayName("재고추가_실패_요청_온_상품이_존재하지않을때")
        @Transactional
        void 재고추가_실패_요청_온_상품이_존재하지않을때(){
            // mocking
            BDDMockito.given(productRepository.findAddStockByProductID(any()))
                    .willReturn(null);

            // when
            Boolean result = productService.stockUpProduct(param, changeStockQuery);

            // then
            Assertions.assertEquals(false, result);
        }

        @Test
        @DisplayName("재고추가_실패_요청_온_상품의_등록자가_아닐때")
        @Transactional
        void 재고추가_실패_요청_온_상품의_등록자가_아닐때(){
            // given
            makeProduct();

            // mocking
            BDDMockito.given(productRepository.findAddStockByProductID(any()))
                    .willReturn(new SelectProductStockQuery(1L, 1, 2L));

            // when
            Boolean result = productService.stockUpProduct(param, changeStockQuery);

            // then
            Assertions.assertEquals(false, result);
        }

        @Test
        @DisplayName("재고추가_실패_상품의_최종_재고가_음수일때")
        @Transactional
        void 재고추가_실패_상품의_최종_재고가_음수일때(){
            // given
            makeProduct();
            ReflectionTestUtils.setField(changeStockQuery, "stock", -2);

            // mocking
            BDDMockito.given(productRepository.findAddStockByProductID(any()))
                    .willReturn(new SelectProductStockQuery(1L, 1, 1L));

            // when
            Boolean result = productService.stockUpProduct(param, changeStockQuery);

            // then
            Assertions.assertEquals(false, result);
        }
    }

    private User makeOther() {
        return new User(
                null,
                "hwang",
                "hs9_good",
                "$2a$10$vG6NvQImCGrMOq78yQiLxuvBt/O7qF7LAH./w9h20WoXoau7EfDgq",
                "010-6612-5161",
                "hs9@gmail.com",
                "sangmyung university - 321",
                Authority.USER
        );
    }

    private void makeUser(){
        param = new User(
                1L,
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
        product_3 = new Product(3L,"오버사이즈 칼라드 스웨트셔츠 [피스타치오]",43900,"top","멋진 상의1 입니다.","S,M,L","https://mallimageupload.s3.ap-northeast-2.amazonaws.com/image/ce1662e0a2b4-11ed-96f9-4f762f37fb59.jpeg",LocalDateTime.now(),46,16, param, 40);
        product_4 = new Product(4L,"쉐기 독 크루 넥 니트",79900,"top","멋진 상의4 입니다.","S,M,L","https://mallimageupload.s3.ap-northeast-2.amazonaws.com/image/e21c4570a2b4-11ed-96f9-4f762f37fb59.jpeg",LocalDateTime.now(),56,52, param, 20);
    }

    private void makeBanner(){
        bannerList = Arrays.asList(
                new Banner(1L, "https://mallimageupload.s3.ap-northeast-2.amazonaws.com/banner/carousel_sample_1.jpg"),
                new Banner(2L, "https://mallimageupload.s3.ap-northeast-2.amazonaws.com/banner/carousel_sample_2.jpg"),
                new Banner(3L, "https://mallimageupload.s3.ap-northeast-2.amazonaws.com/banner/carousel_sample_3.jpg"),
                new Banner(4L, "https://mallimageupload.s3.ap-northeast-2.amazonaws.com/banner/carousel_sample_4.jpg")
        );
    }
}