package com.example.shoppingmall.repository.favorite;

import com.example.shoppingmall.data.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<Favorite, Long>, FavoriteRepositoryCustom {
    Favorite findByUserIdAndProductId(Long userId, Long productId);
}
