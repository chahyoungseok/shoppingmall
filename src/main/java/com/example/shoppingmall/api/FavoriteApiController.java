package com.example.shoppingmall.api;

import com.example.shoppingmall.data.dto.response.ResponseProductSummary;
import com.example.shoppingmall.data.entity.User;
import com.example.shoppingmall.service.FavoriteService;
import com.example.shoppingmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @GetMapping("/user/favorite")
    public ResponseEntity<List<ResponseProductSummary>> myFavorite(HttpServletRequest request){
        User user = (User) request.getAttribute("user");
        List<ResponseProductSummary> productList = favoriteService.myFavorite(user);

        return (!productList.isEmpty()) ?
                ResponseEntity.status(HttpStatus.OK).body(productList) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @PostMapping("/user/favorite/{id}")
    public ResponseEntity<Void> addFavorite(@PathVariable Long id, HttpServletRequest request) {
        productService.increaseFavorite(id);
        boolean check = favoriteService.addFavorite((User) request.getAttribute("user"), id);

        return (check) ?
                ResponseEntity.status(HttpStatus.OK).body(null) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @DeleteMapping("/user/favorite/{id}")
    public ResponseEntity<Void> deleteFavorite(@PathVariable Long id, HttpServletRequest request) {
        productService.decreaseFavorite(id);
        boolean check = favoriteService.deleteFavorite((User) request.getAttribute("user"), id);

        return (check) ?
                ResponseEntity.status(HttpStatus.OK).body(null) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
}
