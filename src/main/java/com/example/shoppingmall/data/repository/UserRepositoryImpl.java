package com.example.shoppingmall.data.repository;

import static com.example.shoppingmall.data.entity.QCart.cart;
import static com.example.shoppingmall.data.entity.QUser.user;
import static com.example.shoppingmall.data.entity.QProduct.product;

import com.example.shoppingmall.data.entity.Product;
import com.example.shoppingmall.data.entity.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public void deleteByUser(String username) {
        List<Product> productList = queryFactory.selectFrom(product)
                .where(product.user.username.eq(username))
                .fetch();

        for(Product p : productList) {
            queryFactory.delete(cart)
                    .where(cart.product.id.eq(p.getId()))
                    .execute();

            queryFactory.delete(product)
                    .where(product.id.eq(p.getId()))
                    .execute();
        }

        queryFactory.delete(user)
                .where(user.username.eq(username))
                .execute();
    }

    @Override
    public User findByUsername(String username) {
        return queryFactory.selectFrom(user)
                .where(user.username.eq(username))
                .fetchOne();
    }
}
