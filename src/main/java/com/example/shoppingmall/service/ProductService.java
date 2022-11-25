package com.example.shoppingmall.service;

import com.example.shoppingmall.data.dto.RequestProduct;
import com.example.shoppingmall.data.dto.ResponseProduct;

import java.util.List;

public interface ProductService {

    ResponseProduct CreateProduct(RequestProduct requestProduct);

    List<ResponseProduct> findAllProduct();


}
