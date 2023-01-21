package com.example.shoppingmall.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Disabled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@Disabled
@Transactional
@SpringBootTest
@AutoConfigureMockMvc
public class BaseControllerTest {

    // Request와 Response에 직접적으로 연결되어있는 Controller에서 Request와 Response를 만들기 위해 사용.
    @Autowired
    public MockMvc mockMvc;

    @Autowired
    public ObjectMapper objectMapper;

}
