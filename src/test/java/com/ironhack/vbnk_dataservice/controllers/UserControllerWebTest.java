package com.ironhack.vbnk_dataservice.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserControllerWebTest {

    @Autowired
    WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;


    private final ObjectMapper objectMapper = new ObjectMapper();
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void get_test() {
    }

    @Test
    void getAdmin_test() {
    }

    @Test
    void getAccountHolder_test() {
    }

    @Test
    void getThirdParty_test() {
    }

    @Test
    void getAllAdmin_test() {
    }

    @Test
    void getAllAccountHolder_test() {
    }

    @Test
    void testGetThirdParty_test() {
    }

    @Test
    void createAccountHolder_test() {
    }

    @Test
    void createAdmin_test() {
    }

    @Test
    void createThirdParty_test() {
    }

    @Test
    void updateAdmin_test() {
    }

    @Test
    void updateAccountHolder_test() {
    }

    @Test
    void updateThirdParty_test() {
    }

    @Test
    void delete_test() {
    }
//    @Test
//    void test_findAll_okOne() throws Exception {
//
//        // we perform the call and check the response code
//        var result = mockMvc
//                .perform(get("/v1/vegetables"))
//                .andExpect(status().isOk()) // check status code 200
//                .andReturn();
//
//        assertTrue(result.getResponse().getContentAsString().contains("Onion")); // check the json
//    }
//
//    @Test
//    void test_create_ok() throws Exception {
//        var broccoli = new Vegetable("Broccoli", new BigDecimal("1.50"), Quality.ORGANIC); // create the object
//        var vegString = objectMapper.writeValueAsString(broccoli); // transform the object to string
//        var result = mockMvc
//                .perform(post("/v1/vegetables")
//                        .content(vegString) // inject the string/object into the requestbody
//                        .contentType(MediaType.APPLICATION_JSON) // tell the client to expect a JSON
//                )
//                .andExpect(status().isCreated())
//                .andReturn();
//        var response = result.getResponse().getContentAsString();
//        assertTrue(response.contains("broccoli"));
//    }
}