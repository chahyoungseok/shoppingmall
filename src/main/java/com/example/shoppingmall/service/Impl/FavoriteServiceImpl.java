package com.example.shoppingmall.service.Impl;

import com.example.shoppingmall.data.dto.response.ResponseFavorite;
import com.example.shoppingmall.data.entity.Favorite;
import com.example.shoppingmall.data.entity.Product;
import com.example.shoppingmall.data.entity.User;
import com.example.shoppingmall.repository.favorite.FavoriteRepository;
import com.example.shoppingmall.repository.product.ProductRepository;
import com.example.shoppingmall.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FavoriteServiceImpl implements FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final ProductRepository productRepository;

    @Autowired
    public FavoriteServiceImpl(FavoriteRepository favoriteRepository, ProductRepository productRepository) {
        this.favoriteRepository = favoriteRepository;
        this.productRepository = productRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResponseFavorite> myFavorite(User user) {

        return favoriteRepository.findAllFavorite(user.getUsername());
    }

    @Override
    public boolean addFavorite(User user, Long productId) {
        Product product = productRepository.findById(productId).orElse(null);
        if (product == null){
            return false;
        }

        Favorite favorite = Favorite.builder()
                .id(null)
                .user(user)
                .product(product)
                .build();

        if (favoriteRepository.findByUserIdAndProductId(user.getId(), productId) != null) {
            return false;
        }

        Favorite createdFavorite = favoriteRepository.save(favorite);
        return !createdFavorite.getProduct().getName().isEmpty();
    }

    @Override
    public boolean deleteFavorite(User user, Long productId) {
        Favorite favorite = favoriteRepository.findByUserIdAndProductId(user.getId(), productId);

        if(favorite == null) {
            return false;
        }

        favoriteRepository.delete(favorite);
        return true;
    }
}
