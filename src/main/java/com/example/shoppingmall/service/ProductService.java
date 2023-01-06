package com.example.shoppingmall.service;

import com.example.shoppingmall.data.dto.request.*;
import com.example.shoppingmall.data.dto.response.ResponseProduct;

import java.util.List;

public interface ProductService {

    ResponseProduct CreateProduct(RequestProduct requestProduct);

    /** 모든 제품 read */
    List<ResponseProduct> findAllProduct();

    /** user_id로 read(유저가 판매등록한 제품 목록) */
    List<ResponseProduct> findByUserId(RequestProductUserId requestProductUserId);

    /** 제품명으로 read(검색 기능 구현?) */
    List<ResponseProduct> findByProductName(RequestProductName requestProductName);

    /** 카테고리별로 read */
    List<ResponseProduct> findByCategory(RequestProductCategory requestProductCategory);

    ResponseProduct updateProduct(RequestProductModify requestProductModify);

    void deleteProduct(Long Id);

}
