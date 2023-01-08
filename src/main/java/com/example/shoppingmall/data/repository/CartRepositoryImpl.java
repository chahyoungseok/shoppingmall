package com.example.shoppingmall.data.repository;

import static com.example.shoppingmall.data.entity.QCart.cart;

import com.example.shoppingmall.data.entity.Cart;
import com.example.shoppingmall.service.Impl.CartServiceImpl;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CartRepositoryImpl implements CartRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Cart> findAllCart(String username) {
        return queryFactory.selectFrom(cart)
                .where(cart.user.username.eq(username))
                .fetch();
    }

    @Override
    @Transactional
    public Boolean findSameCart(Long user_id, Long product_id, int state) {
        Cart cartEntity = queryFactory.selectFrom(cart)
                                .where(cart.user.id.eq(user_id)
                                .and(cart.product.id.eq(product_id)))
                                .fetchOne();

        if(cartEntity == null) {
            return false;
        }

        int state_value = 1;

        if(state == CartServiceImpl.DELETE){
            state_value = -1;
            // 삭제요청에 개수가 1개일때
            if(cartEntity.getCount() == 1) {
                queryFactory.delete(cart)
                        .where(cart.id.eq(cartEntity.getId()))
                        .execute();
                return true;
            }
        }
        queryFactory.update(cart)
                .set(cart.count, cartEntity.getCount() + state_value)
                .where(cart.id.eq(cartEntity.getId()))
                .execute();

        return true;
    }
}
