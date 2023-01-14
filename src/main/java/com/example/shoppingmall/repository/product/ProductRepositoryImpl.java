package com.example.shoppingmall.repository.product;

import com.example.shoppingmall.data.entity.Product;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.example.shoppingmall.data.entity.QProduct.product;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public BooleanExpression eqUsername(String username) {
        if (!StringUtils.hasText(username)){
            return null;
        }
        return product.user.username.eq(username);
    }

    @Override
    public List<Product> selectFromUsername(String username){
        BooleanExpression status = null;
        status = eqUsername(username);

        // BooleanExpression 이 모두 null 이라면 모든 쿼리를 불러오므로 조건처리
        if (status == null) {
            return null;
        }

        return queryFactory.selectFrom(product)
                .where(status)
                .fetch();
    }

    @Override
    public BooleanExpression eqProductID(Long id){
        if (id == null) {
            return null;
        }
        return product.id.eq(id);
    }

    @Override
    public void deleteProductID(Long id){
        BooleanExpression status = null;
        status = eqProductID(id);

        if (status == null) {
            return;
        }

        queryFactory.delete(product)
                .where(status)
                .execute();
    }
}
