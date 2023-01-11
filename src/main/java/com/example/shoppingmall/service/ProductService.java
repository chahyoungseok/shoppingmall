package com.example.shoppingmall.service;

import com.example.shoppingmall.data.dto.request.RequestProduct;
import com.example.shoppingmall.data.dto.request.RequestProductModify;
import com.example.shoppingmall.data.dto.response.ResponseProduct;
import com.example.shoppingmall.data.dto.response.ResponseProductSummary;

import java.util.List;

public interface ProductService {

    /** 메인 페이지 상품 조회 */
    List<List<?>>  mainPageProductList();

    /** 상품명으로 검색 */
    List<ResponseProductSummary> findByProductName(String keyword);

    /** 상품 전체 조회 */
    List<ResponseProductSummary> findAllProduct();

    /** 상품 카테고리별 조회 */
    List<ResponseProductSummary> findByCategory(String category);

    /** 상품 상세 페이지 조회 */
    ResponseProduct findById(Long id);

    /** 판매등록한 상품 목록 조회 */
    List<ResponseProductSummary> findByUsername(String username);

    /** 상품 등록 */
    ResponseProduct CreateProduct(RequestProduct requestProduct);

    /** 상품 정보 수정 페이지 */
    ResponseProduct editProduct(Long id, String username);

    /** 상품 정보 수정 */
    ResponseProduct updateProduct(RequestProductModify requestProductModify);

    /** 상품 삭제 */
    boolean deleteProduct(Long id, String username);

}
