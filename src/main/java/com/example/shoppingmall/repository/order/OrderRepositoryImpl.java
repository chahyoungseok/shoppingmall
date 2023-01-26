package com.example.shoppingmall.repository.order;

import com.example.shoppingmall.data.dto.queryselect.SelectIDQuery;
import com.querydsl.core.types.Projections;
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
    public List<SelectIDQuery> selectIDFromUsername(String username){
        BooleanExpression status = null;
        status = eqUsername(username);

        if (status == null) {
            return null;
        }
        return queryFactory.select(Projections.fields(
                SelectIDQuery.class, order.id))
                .from(order)
                .where(status)
                .fetch();
    }

    @Override
    public BooleanExpression eqOrderIDList(List<Long> IDList){
        if (IDList == null){
            return null;
        }
        return order.id.in(IDList);
    }

    @Override
    public void deleteOrderID(List<Long> IDList){
        BooleanExpression status = null;
        status = eqOrderIDList(IDList);

        if (status == null) {
            return;
        }

        queryFactory.delete(order)
                .where(status)
                .execute();
    }
}
