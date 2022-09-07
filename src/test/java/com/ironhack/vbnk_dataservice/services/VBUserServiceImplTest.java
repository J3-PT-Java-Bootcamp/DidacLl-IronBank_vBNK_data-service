package com.ironhack.vbnk_dataservice.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.vbnk_dataservice.data.Address;
import com.ironhack.vbnk_dataservice.data.dao.AccountHolder;
import com.ironhack.vbnk_dataservice.data.dto.AccountHolderDTO;
import com.ironhack.vbnk_dataservice.repositories.AccountHolderRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static com.ironhack.vbnk_dataservice.data.dao.AccountHolder.newAccountHolder;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
class VBUserServiceImplTest {

    @Autowired
VBUserService service;
@Autowired
    AccountHolderRepository repository;
    @BeforeEach
    void setUp() {
        var address= new Address().setAdditionalInfo("KJSGD").setCity("Oklahoma").setCountry("India")
                .setStreet("Main street").setStreetNumber(45).setZipCode(8080);

        repository.saveAll(List.of(
                newAccountHolder("Antonio","aaa").setDateOfBirth(LocalDate.now()).setPrimaryAddress(address),
                newAccountHolder("Antonia","aab").setDateOfBirth(LocalDate.now()).setPrimaryAddress(address),
                newAccountHolder("Antonino","aac").setDateOfBirth(LocalDate.now()).setPrimaryAddress(address),
                newAccountHolder("Antoine","aad").setDateOfBirth(LocalDate.now()).setPrimaryAddress(address)
        ));

    }

    @AfterEach
    void tearDown() {
//        repository.deleteAll();
    }

    @Test
    void getUnknown_test() {
    }

    @Test
    void getThirdParty_test() {
    }

    @Test
    void getAccountHolder_test() {
    }

    @Test
    void getAdmin_test() {
    }

    @Test
    void update_test() {
    }

    @Test
    void delete_test() {
    }

    @Test
    void getAllAccountHolder_test() {
    }

    @Test
    void getAllThirdParty_test() {
    }

    @Test
    void getAllAdmin_test() {
    }

    @Test
    void create_test() {
    }

}