package com.chessmasters.chessapi.components;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;

@Transactional
@SpringBootTest
public class BaseComponentTest {

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setUp() {
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        RestAssuredMockMvc.mockMvc(mockMvc);
    }
}
