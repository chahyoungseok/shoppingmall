package com.example.shoppingmall.dao.Impl;

import com.example.shoppingmall.dao.ProductDAO;
import com.example.shoppingmall.data.entity.Product;
import com.example.shoppingmall.data.repository.ProductRepository;
import com.example.shoppingmall.data.repository.UserQueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProductDAOImpl implements ProductDAO {

    private final ProductRepository productRepository;
    private final UserQueryRepository userQueryRepository;

    @Autowired
    public ProductDAOImpl(ProductRepository productRepository, UserQueryRepository userQueryRepository){
        this.productRepository = productRepository;
        this.userQueryRepository = userQueryRepository;
    }

    @Override
    public List<Product> findByProductName(String name) {
        return productRepository.findByNameContaining(name);
    }

    @Override
    public List<Product> findAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findByCategory(String category) {
        return productRepository.findByCategory(category);
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
    public List<Product> findByUsername(String username) {
        return productRepository.findByUserId(userQueryRepository.findByUsername(username).getId());
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
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
