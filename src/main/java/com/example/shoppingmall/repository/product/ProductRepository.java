package com.example.shoppingmall.repository.product;

import com.example.shoppingmall.data.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryCustom {

    /** 메인 페이지 상품 조회 */
    List<Product> findTop2ByOrderByIdDesc();

    // Containing 으로 포함 결과 검색
    /** 상품명으로 검색 */
    List<Product> findByNameContaining(String name);

    /** 상품 카테고리별 조회 */
    List<Product> findByCategory(String category);

    /** 판매등록한 상품 목록 조회 */
    List<Product> findByUserId(Long userId);


}
