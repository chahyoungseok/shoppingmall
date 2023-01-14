package com.example.shoppingmall.repository.order;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.example.shoppingmall.data.entity.QOrderProduct.orderProduct;

@Repository
@RequiredArgsConstructor
public class OrderProductRepositoryImpl implements OrderProductRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    @Override
    public BooleanExpression eqOrderID(Long id){
        if (id == null){
            return null;
        }
        return orderProduct.order.id.eq(id);
    }

    @Override
    public void deleteOrderID(Long id){
        BooleanExpression status = null;
        status = eqOrderID(id);

        if (status == null) {
            return;
        }

        queryFactory.delete(orderProduct)
                .where(status)
                .execute();
    }
}
