package com.example.shoppingmall.repository.base;

import com.example.shoppingmall.config.UnitTestConfig;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@Import(UnitTestConfig.class)
@ActiveProfiles("test")
public class BaseRepositoryUnitTest {
}
