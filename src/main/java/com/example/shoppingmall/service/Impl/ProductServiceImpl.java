package com.example.shoppingmall.service.Impl;

import com.example.shoppingmall.dao.ProductDAO;
import com.example.shoppingmall.data.dto.ResponseProduct;
import com.example.shoppingmall.data.entity.Product;
import com.example.shoppingmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductDAO productDAO;

    @Autowired
    public ProductServiceImpl(ProductDAO productDAO){
        this.productDAO = productDAO;
    }

    @Override
    public List<ResponseProduct> findAllproduct() {
        List<Product> product_list = productDAO.findAllproduct();
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
}
