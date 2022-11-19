package com.example.shoppingmall.dao.Impl;

import com.example.shoppingmall.dao.ProductDAO;
import com.example.shoppingmall.data.entity.Product;
import com.example.shoppingmall.data.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductDAOImpl implements ProductDAO {

    private ProductRepository productRepository;

    @Autowired
    public ProductDAOImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAllproduct() {
        return productRepository.findAll();
    }
}
