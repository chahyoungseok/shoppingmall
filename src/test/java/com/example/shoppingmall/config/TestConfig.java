package com.example.shoppingmall.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

// JUnit 만을 사용해 Test를 완성시키기에 JpaQueryFactory가 Spring Bean으로 등록되지않습니다.
// 따라서 해당 코드를 통해 테스트 시 빈 등록을 해줍니다.
@TestConfiguration
public class TestConfig {
    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public JPAQueryFactory jpaQueryFactory(){
        return new JPAQueryFactory(entityManager);
    }
}
