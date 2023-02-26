package com.example.shoppingmall.repository.favorite;

import com.example.shoppingmall.data.dto.response.ResponseFavorite;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
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
    public List<ResponseFavorite> findAllFavorite(String username) {
        BooleanExpression status_user = eqUsername(username);

        if (status_user == null) {
            return null;
        }

        return queryFactory.select(Projections.fields(ResponseFavorite.class,
                        favorite.product.id,
                        favorite.product.name,
                        favorite.product.price,
                        favorite.product.favorite,
                        favorite.product.imgKey,
                        new CaseBuilder()
                                .when(product.stock.gt(0))
                                .then(true)
                                .otherwise(false).as("stock_zero")
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

    @Override
    public BooleanExpression eqUserID(Long user_id){
        if (user_id == null) {
            return null;
        }
        return favorite.user.id.eq(user_id);
    }

    @Override
    public BooleanExpression eqProductID(Long product_id){
        if (product_id == null) {
            return null;
        }
        return favorite.product.id.eq(product_id);
    }

    @Override
    public Boolean existUserProductByFavorite(Long user_id, Long product_id) {
        BooleanExpression status_user = eqUserID(user_id);
        BooleanExpression status_product = eqProductID(product_id);

        if (status_user == null || status_product == null) {
            return null;
        }

        // querydsl에서의 exists 함수는 subQuery에서만 사용가능한 함수이므로 해당 함수로 대체한다.
        return queryFactory.from(favorite)
                .where(status_user, status_product)
                .fetchFirst() != null;
    }
}
