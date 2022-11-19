package com.example.shoppingmall.service;

import com.example.shoppingmall.data.dto.ResponseProduct;
import com.example.shoppingmall.data.entity.Product;

import java.util.List;

public interface ProductService {

    public List<ResponseProduct> findAllproduct();
}
