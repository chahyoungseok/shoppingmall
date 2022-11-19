package com.example.shoppingmall.api;

import com.example.shoppingmall.data.dto.ResponseProduct;
import com.example.shoppingmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductApiController {
    @Autowired
    private ProductService productService;

    @GetMapping({"/", ""})
    public ResponseEntity<List<ResponseProduct>> main(){
        List<ResponseProduct> products = productService.findAllproduct();
        return (products != null) ?
                ResponseEntity.status(HttpStatus.OK).body(products) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
}
