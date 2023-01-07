package com.example.shoppingmall.data.repository;

import com.example.shoppingmall.data.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // Containing 으로 포함 결과 검색
    /** 상품명으로 검색 */
    List<Product> findByNameContaining(String name);

    /** 판매등록한 상품 목록 조회 */
    List<Product> findByUserId(Long userId);

    /** 상품 카테고리별 조회 */
    List<Product> findByCategory(String category);
}
