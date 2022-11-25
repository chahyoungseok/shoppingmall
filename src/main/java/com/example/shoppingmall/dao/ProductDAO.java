package com.example.shoppingmall.dao;

import com.example.shoppingmall.data.entity.Product;

import java.util.List;

public interface ProductDAO {
    Product CreateProduct(Product product);

    List<Product> findAllProduct();

}
