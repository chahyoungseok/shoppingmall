package com.example.shoppingmall.dao;

import com.example.shoppingmall.data.entity.Product;
import com.example.shoppingmall.data.entity.User;

import java.util.List;

public interface ProductDAO {
    Product CreateProduct(Product product);

    List<Product> findAllProduct();

    List<Product> findByUserId(Long user_id);

    List<Product> findByProductName(String name);

    Product findById(Long id);

    Product updateProduct(Product product);

    void deleteProduct(Product product);

}
