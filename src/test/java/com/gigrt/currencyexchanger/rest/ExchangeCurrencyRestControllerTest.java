package com.gigrt.currencyexchanger.rest;

import com.gigrt.currencyexchanger.api.ApiUrl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ExchangeCurrencyRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAllExchangeCurrencies() throws Exception {
//        mvc.perform(MockMvcRequestBuilders.get("/v1/currency-exchange").accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//                .andExpect(content().string(equalTo("Greetings from Spring Boot!")));
    }

    @Test
    void getConvertedAmount() {
    }

    @Test
    void createTransaction() {
    }
}