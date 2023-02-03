package com.example.shoppingmall.repository.product;

import com.example.shoppingmall.data.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryCustom {

    /** 메인 페이지 상품 조회 */
    List<Product> findTop2ByOrderByIdDesc();

    /** 상품 전체 조회수 높은순으로 조회 */
    List<Product> findAllByOrderByHitsDesc();

    /** 상품 전체 조회수 최신순으로 조회 */
    List<Product> findAllByOrderByDateDesc();

    // Containing 으로 포함 결과 검색
    /** 상품명으로 검색 */
    List<Product> findByNameContaining(String name);

    /** 상품 카테고리별 조회 */
    List<Product> findByCategory(String category);

    /** 판매등록한 상품 목록 조회 */
    List<Product> findByUserId(Long userId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Product o set o.hits=o.hits+1 where o.id=:id")
    void increaseHits(@Param("id") Long id);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Product o set o.favorite=o.favorite+1 where o.id=:id")
    void increaseFavorite(@Param("id") Long id);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Product o set o.favorite=o.favorite-1 where o.id=:id")
    void decreaseFavorite(@Param("id") Long id);
}
