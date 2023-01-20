package com.example.shoppingmall.repository.cart;

import com.example.shoppingmall.aop.annotation.RunningTime;
import com.example.shoppingmall.data.entity.Cart;
import com.example.shoppingmall.service.Impl.CartServiceImpl;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jdk.jfr.Timestamp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

import static com.example.shoppingmall.data.entity.QCart.cart;
import static com.example.shoppingmall.data.entity.QUser.user;
import static com.example.shoppingmall.data.entity.QProduct.product;

@Repository
@RequiredArgsConstructor
public class CartRepositoryImpl implements CartRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    @RunningTime
    public List<Cart> findAllCart(String username) {
        return queryFactory.selectFrom(cart)
                .innerJoin(cart.product, product)
                .where(cart.user.username.eq(username))
                .fetchJoin()
                .innerJoin(cart.user, user)
                .fetchJoin()
                .distinct()
                .fetch();
    }

    @Override
    @Transactional
    public Boolean findSameCart(Long user_id, Long product_id, int state) {
        Cart cartEntity = selectFromUserID_N_ProductID(user_id, product_id);

        if(cartEntity == null) {
            return false;
        }

        int state_value = 1;

        if(state == CartServiceImpl.DELETE){
            state_value = -1;
            // 삭제요청에 개수가 1개일때
            if(cartEntity.getCount() == 1) {
                deleteCartID(cartEntity.getId());
                return true;
            }
        }

        updateCartID(cartEntity.getId(), cartEntity.getCount() + state_value);
        return true;
    }

    @Override
    public BooleanExpression eqUserID(Long id){
        if (id == null) {
            return null;
        }
        return cart.user.id.eq(id);
    }

    @Override
    public BooleanExpression eqProductID(Long id){
        if (id == null) {
            return null;
        }
        return cart.product.id.eq(id);
    }

    @Override
    public BooleanExpression eqCartID(Long id){
        if (id == null) {
            return null;
        }
        return cart.id.eq(id);
    }

    @Override
    public Cart selectFromUserID_N_ProductID(Long user_id, Long product_id){
        BooleanExpression status_user = eqUserID(user_id);
        BooleanExpression status_product = eqProductID(product_id);

        if (status_user == null && status_product == null) {
            return null;
        }

        return queryFactory.selectFrom(cart)
                .where(status_user, status_product)
                .fetchOne();
    }

    @Override
    public void updateCartID(Long id, int updated_value){
        BooleanExpression status = null;
        status = eqCartID(id);

        if (status == null) {
            return;
        }

        queryFactory.update(cart)
                .set(cart.count, updated_value)
                .where(status)
                .execute();
    }

    @Override
    public void deleteProductID(Long id){
        BooleanExpression status = null;
        status = eqProductID(id);

        if (status == null) {
            return;
        }

        queryFactory.delete(cart)
                .where(status)
                .execute();
    }

    @Override
    public void deleteCartID(Long id){
        BooleanExpression status = null;
        status = eqCartID(id);

        if (status == null) {
            return;
        }

        queryFactory.delete(cart)
                .where(status)
                .execute();
    }
}
