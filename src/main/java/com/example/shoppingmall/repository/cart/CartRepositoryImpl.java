package com.example.shoppingmall.repository.cart;

import com.example.shoppingmall.data.dto.queryselect.SelectCart;
import com.example.shoppingmall.data.dto.response.ResponseCart;
import com.example.shoppingmall.service.Impl.CartServiceImpl;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

import static com.example.shoppingmall.data.entity.QUser.user;
import static com.example.shoppingmall.data.entity.QCart.cart;
import static com.example.shoppingmall.data.entity.QProduct.product;

@Repository
@RequiredArgsConstructor
public class CartRepositoryImpl implements CartRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ResponseCart> findAllCart(String username) {
        BooleanExpression status_user = eqUsername(username);

        if (status_user == null) {
            return null;
        }

        return queryFactory.select(Projections.fields(ResponseCart.class,
                    product.id,
                    product.name,
                    product.price,
                    cart.size,
                    product.imgKey,
                    cart.count,
                    new CaseBuilder()
                            .when(product.stock.gt(0))
                            .then(true)
                            .otherwise(false).as("stock_zero")
                ))
                .from(cart)
                .innerJoin(cart.product, product)
                .innerJoin(cart.user, user)
                .where(status_user)
                .fetch();
    }

    @Override
    @Transactional
    public Boolean findSameCart(Long user_id, Long product_id, String size, int state) {
        SelectCart selectCart = selectFromUserID_N_ProductID(user_id, product_id, size);

        if(selectCart == null) {
            return false;
        }

        int state_value = 1;

        if(state == CartServiceImpl.DELETE){
            state_value = -1;
            // 삭제요청에 개수가 1개일때
            if(selectCart.getCount() == 1) {
                deleteCartID(selectCart.getId());
                return true;
            }
        }

        updateCartID(selectCart.getId(), selectCart.getCount() + state_value);
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
    public BooleanExpression eqUsername(String username){
        if (username == null) {
            return null;
        }
        return cart.user.username.eq(username);
    }

    @Override
    public BooleanExpression eqProductID(Long id){
        if (id == null) {
            return null;
        }
        return cart.product.id.eq(id);
    }

    @Override
    public BooleanExpression eqSize(String size){
        if (size == null) {
            return null;
        }
        return cart.size.eq(size);
    }

    @Override
    public BooleanExpression eqCartID(Long id){
        if (id == null) {
            return null;
        }
        return cart.id.eq(id);
    }

    @Override
    public SelectCart selectFromUserID_N_ProductID(Long user_id, Long product_id, String size){
        BooleanExpression status_user = eqUserID(user_id);
        BooleanExpression status_product = eqProductID(product_id);
        BooleanExpression status_size = eqSize(size);

        if (status_user == null || status_product == null || status_size == null) {
            return null;
        }

        return queryFactory.select(Projections.fields(SelectCart.class,
                        cart.id,
                        cart.count,
                        Expressions.asString(size).as("size"),
                        Expressions.asNumber(user_id).as("user_id"),
                        Expressions.asNumber(product_id).as("product_id")))
                .from(cart)
                .where(status_user, status_product, status_size)
                .fetchOne();
    }

    @Override
    public void updateCartID(Long id, int updated_value){
        BooleanExpression status = eqCartID(id);

        if (status == null) {
            return;
        }

        queryFactory.update(cart)
                .set(cart.count, updated_value)
                .where(status)
                .execute();
    }

    @Override
    public void deleteCartID(Long id){
        BooleanExpression status = eqCartID(id);

        if (status == null) {
            return;
        }

        queryFactory.delete(cart)
                .where(status)
                .execute();
    }

    @Override
    @Transactional
    public Boolean deleteAllCart(Long user_id, Long product_id, String size) {
        SelectCart selectCart = selectFromUserID_N_ProductID(user_id, product_id, size);

        if(selectCart == null) {
            return false;
        }

        deleteCartID(selectCart.getId());
        return true;
    }
}
