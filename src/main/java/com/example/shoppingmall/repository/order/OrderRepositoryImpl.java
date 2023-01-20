package com.example.shoppingmall.repository.order;

import com.example.shoppingmall.data.entity.Order;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.example.shoppingmall.data.entity.QOrder.order;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    @Override
    public BooleanExpression eqUsername(String username) {
        if (!StringUtils.hasText(username)){
            return null;
        }
        return order.user.username.eq(username);
    }

    @Override
    public List<Order> selectFromUsername(String username){
        BooleanExpression status = null;
        status = eqUsername(username);

        if (status == null) {
            return null;
        }
        return queryFactory.selectFrom(order)
                .where(status)
                .fetch();
    }

    @Override
    public BooleanExpression eqOrderID(Long id){
        if (id == null){
            return null;
        }
        return order.id.eq(id);
    }

    @Override
    public void deleteOrderID(Long id){
        BooleanExpression status = null;
        status = eqOrderID(id);

        if (status == null) {
            return;
        }

        queryFactory.delete(order)
                .where(status)
                .execute();
    }
}
