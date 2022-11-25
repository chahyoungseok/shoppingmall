package com.example.shoppingmall.api;

import com.example.shoppingmall.data.dto.RequestProduct;
import com.example.shoppingmall.data.dto.ResponseProduct;
import com.example.shoppingmall.data.entity.Product;
import com.example.shoppingmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductApiController {
    @Autowired
    private ProductService productService;

    @PostMapping("/createProduct")
    public ResponseEntity<ResponseProduct> createProduct(@RequestBody RequestProduct requestProduct){
        System.out.println("pro : " + requestProduct);
        ResponseProduct product = productService.CreateProduct(requestProduct);
        return (product != null) ?
                ResponseEntity.status(HttpStatus.OK).body(product) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @GetMapping("/findAllProduct")
    public ResponseEntity<List<ResponseProduct>> findAllProduct(){
        List<ResponseProduct> products = productService.findAllProduct();
//        System.out.println("pro : " + products);
        return (products != null) ?
                ResponseEntity.status(HttpStatus.OK).body(products) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }





}
