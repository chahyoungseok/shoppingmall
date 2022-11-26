package com.example.shoppingmall.api;

import com.example.shoppingmall.data.dto.*;
import com.example.shoppingmall.data.entity.Product;
import com.example.shoppingmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/findByUserId")
    public ResponseEntity<List<ResponseProduct>> findByUserId(RequestProductUserId requestProductUserId){
        System.out.println("controller1 : " + requestProductUserId);
        List<ResponseProduct> products = productService.findByUserId(requestProductUserId);
        System.out.println("controller2 : " + products);
        return (products != null) ?
                ResponseEntity.status(HttpStatus.OK).body(products) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
    @GetMapping("/findByProductName")
    public ResponseEntity<List<ResponseProduct>> findByProductName(RequestProductName requestProductName){
        System.out.println("controller1 : " + requestProductName);
        List<ResponseProduct> products = productService.findByProductName(requestProductName);
        System.out.println("controller2 : " + products);
        return (products != null) ?
                ResponseEntity.status(HttpStatus.OK).body(products) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @PutMapping("/updateProduct")
    public ResponseEntity<ResponseProduct> updateProduct(@RequestBody RequestProductModify requestProductModify) {
        ResponseProduct responseProduct = productService.updateProduct(requestProductModify);
        return (responseProduct != null) ?
                ResponseEntity.status(HttpStatus.OK).body(responseProduct) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @DeleteMapping("/deleteProduct/{id}")
    public String deleteUser(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/";
    }



}
