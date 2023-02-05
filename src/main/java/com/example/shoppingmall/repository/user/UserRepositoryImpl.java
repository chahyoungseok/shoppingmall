package com.example.shoppingmall.repository.user;

import com.example.shoppingmall.data.entity.User;
import com.example.shoppingmall.repository.product.ProductRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.List;

import static com.example.shoppingmall.data.entity.QUser.user;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Autowired
    private ProductRepository productRepository;

    @Override
    @Transactional
    public void deleteByUser(String username) {
        List<Long> productIDList = productRepository.selectIDFromUsername(username)
                .stream().map(selectID -> selectID.getId()).toList();

        productRepository.deleteProductIDList(productIDList);

        deleteUsername(username);
    }

    @Override
    public User findByUsername(String username) {
        return queryFactory.selectFrom(user)
                .where(user.username.eq(username))
                .fetchOne();
    }

    @Override
    public BooleanExpression eqUsername(String username){
        if(!StringUtils.hasText(username)){
            return null;
        }
        return user.username.eq(username);
    }

    @Override
    public void deleteUsername(String username){
        BooleanExpression status = null;
        status = eqUsername(username);

        if (status == null) {
            return;
        }

        queryFactory.delete(user)
                .where(status)
                .execute();
    }
}
