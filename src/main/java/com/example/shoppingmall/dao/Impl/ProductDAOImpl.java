package com.example.shoppingmall.dao.Impl;

import com.example.shoppingmall.dao.ProductDAO;
import com.example.shoppingmall.data.entity.Product;
import com.example.shoppingmall.data.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class ProductDAOImpl implements ProductDAO {

    private ProductRepository productRepository;

    @Autowired
    public ProductDAOImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public Product CreateProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> findAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findByUserId(Long user_id) {
        return productRepository.findByUserId(user_id);
    }

    @Override
    public List<Product> findByProductName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public Product findById(Long id) {
        Optional<Product> optional_product = productRepository.findById(id);
        Product product = new Product();
        if(optional_product.isPresent()){
            product = optional_product.get();
        }
        return product;
    }

    @Override
    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Product product) {
        productRepository.delete(product);
    }

}
