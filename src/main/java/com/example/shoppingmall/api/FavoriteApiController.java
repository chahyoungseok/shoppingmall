package com.example.shoppingmall.api;

import com.example.shoppingmall.aop.annotation.UserAnnotation;
import com.example.shoppingmall.data.dto.response.ResponseFavorite;
import com.example.shoppingmall.data.entity.User;
import com.example.shoppingmall.service.FavoriteService;
import com.example.shoppingmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
public class FavoriteApiController {

    private final FavoriteService favoriteService;
    private final ProductService productService;

    @Autowired
    public FavoriteApiController(FavoriteService favoriteService, ProductService productService) {
        this.favoriteService = favoriteService;
        this.productService = productService;
    }

    /** 좋아요 목록 가져오기 */
    @GetMapping("/user/favorite")
    public ResponseEntity<List<ResponseFavorite>> myFavorite(@UserAnnotation User user){
        List<ResponseFavorite> productList = favoriteService.myFavorite(user);

        return (!productList.isEmpty()) ?
                ResponseEntity.status(HttpStatus.OK).body(productList) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    /** 상품 좋아요 등록 */
    @Transactional
    @PostMapping("/user/favorite/{id}")
    public ResponseEntity<Void> addFavorite(@PathVariable Long id, @UserAnnotation User user) {
        productService.increaseFavorite(id);
        boolean check = favoriteService.addFavorite(user, id);

        return (check) ?
                ResponseEntity.status(HttpStatus.OK).body(null) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    /** 상품 좋아요 해제 */
    @Transactional
    @DeleteMapping("/user/favorite/{id}")
    public ResponseEntity<Void> deleteFavorite(@PathVariable Long id, @UserAnnotation User user) {
        productService.decreaseFavorite(id);
        boolean check = favoriteService.deleteFavorite(user, id);

        return (check) ?
                ResponseEntity.status(HttpStatus.OK).body(null) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
}
