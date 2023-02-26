package com.example.shoppingmall.service;

import com.example.shoppingmall.data.dto.response.ResponseFavorite;
import com.example.shoppingmall.data.entity.User;

import java.util.List;

public interface FavoriteService {
    /** 유저가 좋아요 한 상품 조회 */
    List<ResponseFavorite> myFavorite(User user);

    /** 좋아요 누른 상품 추가 */
    boolean addFavorite(User user, Long productId);

    /** 좋아요 누른 상품에서 삭제 */
    boolean deleteFavorite(User user, Long productId);
}
