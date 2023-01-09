package com.example.shoppingmall.api;

import com.example.shoppingmall.data.dto.request.*;
import com.example.shoppingmall.data.dto.response.ResponseProduct;
import com.example.shoppingmall.data.dto.response.ResponseProductSummary;
import com.example.shoppingmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class ProductApiController {
    @Autowired
    private ProductService productService;

    /** 메인 페이지 */
    @GetMapping("/")
    public ResponseEntity<List<ResponseProduct>> mainPage(){
//        List<ResponseProduct> productList = productService.mainPageProductList();
//        배너리스트에서 이미지 키 값 가져오는 함수
//        return (productList != null) ?
//                ResponseEntity.status(HttpStatus.OK).body(productList) :
//                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        return null;
    }

    /** 상품명으로 검색 */
    @GetMapping("/shop/search/{keyword}")
    public ResponseEntity<List<ResponseProductSummary>> findByProductName(@PathVariable String keyword){
        List<ResponseProductSummary> productList = productService.findByProductName(keyword);
        return (productList.size() != 0) ?
                ResponseEntity.status(HttpStatus.OK).body(productList) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    /** 상품 전체 조회 */
    @GetMapping("/shop")
    public ResponseEntity<List<ResponseProductSummary>> findAllProduct(){
        List<ResponseProductSummary> productList = productService.findAllProduct();
        return (productList != null) ?
                ResponseEntity.status(HttpStatus.OK).body(productList) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    /** 상품 카테고리별 조회 */
    @GetMapping("/shop/category/{category}")
    public ResponseEntity<List<ResponseProductSummary>> findByCategory(@PathVariable String category){
        List<ResponseProductSummary>productList = productService.findByCategory(category);
        return (productList != null) ?
                ResponseEntity.status(HttpStatus.OK).body(productList) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    /** 상품 상세 페이지 조회 */
    @GetMapping("/shop/detail/{id}")
    public ResponseEntity<ResponseProduct> findById(@PathVariable Long id){
        ResponseProduct product = productService.findById(id);
        return (product != null) ?
                ResponseEntity.status(HttpStatus.OK).body(product) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    /** 판매등록한 상품 목록 조회 */
    @GetMapping("/register/read_product")
    public ResponseEntity<List<ResponseProductSummary>> findByUsername(HttpServletRequest request){
        List<ResponseProductSummary> productList = productService.findByUsername(request.getAttribute("username").toString());
        return (productList != null) ?
                ResponseEntity.status(HttpStatus.OK).body(productList) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    /** 상품 등록 */
    @PostMapping("/register/create_product")
    public ResponseEntity<ResponseProduct> createProduct(@RequestBody RequestProduct requestProduct, HttpServletRequest request){
        requestProduct.setUsername(request.getAttribute("username").toString());
        ResponseProduct product = productService.CreateProduct(requestProduct);
        return (product != null) ?
                ResponseEntity.status(HttpStatus.OK).body(product) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    /** 상품 정보 수정 페이지 */
    @GetMapping("/register/edit_product/{id}")
    public ResponseEntity<ResponseProduct> editProduct(@PathVariable Long id, HttpServletRequest request){
        ResponseProduct product = productService.editProduct(id, request.getAttribute("username").toString());
        return (product != null) ?
                ResponseEntity.status(HttpStatus.OK).body(product) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    /** 상품 정보 수정 */
    @PutMapping("/register/update_product")
    public ResponseEntity<ResponseProduct> updateProduct(@RequestBody RequestProductModify requestProductModify, HttpServletRequest request) {
        requestProductModify.setUsername(request.getAttribute("username").toString());
        ResponseProduct product = productService.updateProduct(requestProductModify);
        return (product != null) ?
                ResponseEntity.status(HttpStatus.OK).body(product) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    /** 상품 삭제 */
    @DeleteMapping("/register/deleteProduct/{id}")
    public ResponseEntity<Boolean> deleteProduct(@PathVariable Long id, HttpServletRequest request) {
        boolean check = productService.deleteProduct(id, request.getAttribute("username").toString());
        return (check) ?
                ResponseEntity.status(HttpStatus.OK).body(true) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
    }

}
