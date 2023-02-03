package com.example.shoppingmall.repository.favorite;

import com.example.shoppingmall.data.dto.response.ResponseProductSummary;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.shoppingmall.data.entity.QFavorite.favorite;
import static com.example.shoppingmall.data.entity.QProduct.product;
import static com.example.shoppingmall.data.entity.QUser.user;

@Repository
@RequiredArgsConstructor
public class FavoriteRepositoryImpl implements FavoriteRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ResponseProductSummary> findAllFavorite(String username) {
        BooleanExpression status_user = eqUsername(username);

        if (status_user == null) {
            return null;
        }

        return queryFactory.select(Projections.fields(ResponseProductSummary.class,
                        favorite.product.id,
                        favorite.product.name,
                        favorite.product.price,
                        favorite.product.imgKey,
                        favorite.product.favorite
                ))
                .from(favorite)
                .innerJoin(favorite.product, product)
                .innerJoin(favorite.user, user)
                .where(status_user)
                .fetch();
    }

    @Override
    public BooleanExpression eqUsername(String username){
        if (username == null) {
            return null;
        }
        return favorite.user.username.eq(username);
    }
}
