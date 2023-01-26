package com.example.shoppingmall.repository.cart;

import com.example.shoppingmall.aop.annotation.RunningTime;
import com.example.shoppingmall.data.dto.response.ResponseCart;
import com.example.shoppingmall.data.entity.Cart;
import com.example.shoppingmall.service.Impl.CartServiceImpl;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

import static com.example.shoppingmall.data.entity.QCart.cart;
import static com.example.shoppingmall.data.entity.QProduct.product;

@Repository
@RequiredArgsConstructor
public class CartRepositoryImpl implements CartRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    @RunningTime
    public List<ResponseCart> findAllCart(String username) {
        return queryFactory.select(Projections.fields(ResponseCart.class,
                    product.id,
                    product.name,
                    product.price,
                    product.size,
                    product.imgKey,
                    cart.count
                ))
                .from(cart)
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

    public BooleanExpression eqProductIDList(List<Long> IDList){
        if (IDList == null) {
            return null;
        }
        return cart.product.id.in(IDList);
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

        return queryFactory.select(Projections.fields(Cart.class,
                        cart.id,
                        cart.count,
                        Expressions.asNumber(user_id).as("user_id"),
                        Expressions.asNumber(product_id).as("product_id")))
                .from(cart)
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
    public void deleteProductID(List<Long> IDList){
        BooleanExpression status = null;
        status = eqProductIDList(IDList);

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
