package com.example.shoppingmall.service.Impl;

import com.example.shoppingmall.dao.ProductDAO;
import com.example.shoppingmall.dao.UserDAO;
import com.example.shoppingmall.data.dto.*;
import com.example.shoppingmall.data.entity.Product;
import com.example.shoppingmall.data.entity.User;
import com.example.shoppingmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductDAO productDAO;
    private final UserDAO userDAO;

    @Autowired
    public ProductServiceImpl(ProductDAO productDAO, UserDAO userDAO){
        this.productDAO = productDAO;
        this.userDAO = userDAO;
    }

    @Override
    public ResponseProduct CreateProduct(RequestProduct requestProduct){
        // Dto -> Entity
        Product product = new Product();
        product.setId(requestProduct.getId());
        product.setName(requestProduct.getName());
        product.setPrice(requestProduct.getPrice());
        product.setUser(userDAO.findById(requestProduct.getUserId()));
        System.out.println("service : " + product);
        Product createdProduct = productDAO.CreateProduct(product);

        // Entity -> Dto
        ResponseProduct responseProduct = new ResponseProduct();
        responseProduct.setId(createdProduct.getId());
        responseProduct.setName(createdProduct.getName());
        responseProduct.setPrice(createdProduct.getPrice());
        responseProduct.setUserId(createdProduct.getUser().getId());

        return responseProduct;
    }

    @Override
    public List<ResponseProduct> findAllProduct() {
        List<Product> product_list = productDAO.findAllProduct();
        if (product_list == null){
            return null;
        }

        List<ResponseProduct> responseProduct_list = new ArrayList<>();

        for(Product product : product_list){
            ResponseProduct newDto = new ResponseProduct();
            newDto.setId(product.getId());
            newDto.setName(product.getName());
            newDto.setUserId(product.getUser().getId());
            newDto.setPrice(product.getPrice());

            responseProduct_list.add(newDto);
        }

        return responseProduct_list;
    }

    @Override
    public List<ResponseProduct> findByUserId(RequestProductUserId requestProductUserId) {
        // Dto -> Entity
        List<Product> productList = productDAO.findByUserId(requestProductUserId.getUserId());

        // Entity -> Dto
        List<ResponseProduct> responseProductList = new ArrayList<>();
        for(Product product: productList){
            ResponseProduct newDto = new ResponseProduct();
            newDto.setId(product.getId());
            newDto.setName(product.getName());
            newDto.setUserId(product.getUser().getId());
            newDto.setPrice(product.getPrice());

            responseProductList.add(newDto);
        }
        return responseProductList;
    }

    @Override
    public List<ResponseProduct> findByProductName(RequestProductName requestProductName) {
        // Dto -> Entity
        List<Product> productList = productDAO.findByProductName(requestProductName.getName());

        // Entity -> Dto
        List<ResponseProduct> responseProductList = new ArrayList<>();
        for(Product product: productList){
            ResponseProduct newDto = new ResponseProduct();
            newDto.setId(product.getId());
            newDto.setName(product.getName());
            newDto.setUserId(product.getUser().getId());
            newDto.setPrice(product.getPrice());

            responseProductList.add(newDto);
        }
        return responseProductList;
    }

    @Override
    public ResponseProduct updateProduct(RequestProductModify requestProductModify) {
        // Dto -> Entity
        Product product = productDAO.findById(requestProductModify.getId());
        product.setName(requestProductModify.getName());
        product.setPrice(requestProductModify.getPrice());

        Product modified_Product = productDAO.updateProduct(product);

        // Entity -> Dto
        ResponseProduct responseProduct = new ResponseProduct();
        responseProduct.setId(modified_Product.getId());
        responseProduct.setName(modified_Product.getName());
        responseProduct.setPrice(modified_Product.getPrice());
        responseProduct.setUserId(modified_Product.getUser().getId());
        return responseProduct;
    }

    @Override
    public void deleteProduct(Long Id) {
        Product product = productDAO.findById(Id);
        productDAO.deleteProduct(product);
    }



}
