package com.example.shoppingmall.dao;

import com.example.shoppingmall.data.entity.Product;

import java.util.List;

public interface ProductDAO {

    /** 상품명으로 검색 */
    List<Product> findByProductName(String productName);

    /** 상품 전체 조회 */
    List<Product> findAllProduct();

    /** 상품 카테고리별 조회 */
    List<Product> findByCategory(String category);

    /**
     * 상품 상세 페이지 조회,
     * (edit, update,delete 할 때 product 가져오기)
     */
    Product findById(Long productId);

    /** 판매등록한 상품 목록 조회 */
    List<Product> findByUsername(String username);

    /** 상품 등록 */
    Product createProduct(Product product);

    /** 상품 정보 수정 */
    Product updateProduct(Product product);

    /** 상품 삭제 */
    void deleteProduct(Product product);

}
