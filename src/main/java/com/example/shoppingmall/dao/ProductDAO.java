package com.example.shoppingmall.dao;

import com.example.shoppingmall.data.entity.Product;
import com.example.shoppingmall.data.entity.User;

import java.util.List;

public interface ProductDAO {
    Product CreateProduct(Product product);

    /** 모든 제품 read */
    List<Product> findAllProduct();

    /** user_id로 read(유저가 판매등록한 제품 목록) */
    List<Product> findByUserId(Long userId);

    /** 제품명으로 read(검색 기능 구현?) */
    List<Product> findByProductName(String productName);

    /** 카테고리별로 read */
    List<Product> findByCategory(String category);

    /** product_id로 product 검색
        (update,delete 할 때 product 가져오기)
        (제품 상세페이지 load)
     */
    Product findById(Long productId);

    Product updateProduct(Product product);

    void deleteProduct(Product product);

}
