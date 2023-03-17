package com.example.shoppingmall.api;

import com.example.shoppingmall.aop.annotation.UserAnnotation;
import com.example.shoppingmall.data.dto.request.RequestSize;
import com.example.shoppingmall.data.dto.response.ResponseCart;
import com.example.shoppingmall.data.entity.User;
import com.example.shoppingmall.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CartApiController {

    private final CartService cartService;
    @Autowired
    public CartApiController(CartService cartService) {
        this.cartService = cartService;
    }

    /** 장바구니 목록 조회 */
    @GetMapping("/user/cart")
    public ResponseEntity<List<ResponseCart>> readCart(@UserAnnotation User user) {
        List<ResponseCart> cartList = cartService.readCart(user);
        return (cartList != null) ?
                ResponseEntity.status(HttpStatus.OK).body(cartList) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    /** 장바구니 상품 추가 */
    @PostMapping("/user/cart/{id}")
    public ResponseEntity<List<ResponseCart>> createCart(@UserAnnotation User user, @PathVariable Long id, @RequestBody RequestSize requestSize) {
        List<ResponseCart> cartList = cartService.createCart(user, id, requestSize.getSize());
        return (cartList != null) ?
                ResponseEntity.status(HttpStatus.OK).body(cartList) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    /** 장바구니 상품 삭제 */
    @DeleteMapping("/user/cart/{id}")
    public ResponseEntity<List<ResponseCart>> deleteCart(@UserAnnotation User user, @PathVariable Long id, @RequestParam(value = "size") String size) {
        List<ResponseCart> cartList = cartService.deleteCart(user, id, size);
        return (cartList != null) ?
                ResponseEntity.status(HttpStatus.OK).body(cartList) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    /** 장바구니 특정상품 전체삭제 */
    @DeleteMapping("/user/cart/all/{id}")
    public ResponseEntity<List<ResponseCart>> deleteAllCart(@UserAnnotation User user, @PathVariable Long id, @RequestParam(value = "size") String size) {
        List<ResponseCart> cartList = cartService.deleteAllCart(user, id, size);
        return (cartList != null) ?
                ResponseEntity.status(HttpStatus.OK).body(cartList) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
}
