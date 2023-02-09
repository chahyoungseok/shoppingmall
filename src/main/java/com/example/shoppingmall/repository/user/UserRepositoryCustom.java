package com.example.shoppingmall.repository.user;

import com.example.shoppingmall.data.entity.User;
import com.querydsl.core.types.dsl.BooleanExpression;

public interface UserRepositoryCustom {

    void deleteByUser(String username);

    User findByUsername(String username);

    BooleanExpression eqUsername(String username);

    void deleteUsername(String username);
}
