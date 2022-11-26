package com.example.shoppingmall.service;

import com.example.shoppingmall.data.dto.*;
import com.example.shoppingmall.data.entity.Product;

import java.util.List;

public interface ProductService {

    ResponseProduct CreateProduct(RequestProduct requestProduct);

    List<ResponseProduct> findAllProduct();

    List<ResponseProduct> findByUserId(RequestProductUserId requestProductUserId);

    List<ResponseProduct> findByProductName(RequestProductName requestProductName);

    ResponseProduct updateProduct(RequestProductModify requestProductModify);

    void deleteProduct(Long Id);

}
