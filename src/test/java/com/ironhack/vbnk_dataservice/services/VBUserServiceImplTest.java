package com.ironhack.vbnk_dataservice.services;

import com.ironhack.vbnk_dataservice.data.Address;
import com.ironhack.vbnk_dataservice.data.dto.users.AccountHolderDTO;
import com.ironhack.vbnk_dataservice.repositories.users.AccountHolderRepository;
import com.ironhack.vbnk_dataservice.repositories.users.AdminRepository;
import com.ironhack.vbnk_dataservice.repositories.users.ThirdPartyRepository;
import org.apache.http.HttpException;
import org.apache.http.client.HttpResponseException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.List;

import static com.ironhack.vbnk_dataservice.data.dao.users.AccountHolder.newAccountHolder;
import static com.ironhack.vbnk_dataservice.data.dao.users.ThirdParty.newThirdParty;
import static com.ironhack.vbnk_dataservice.data.dao.users.VBAdmin.newVBAdmin;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class VBUserServiceImplTest {

    @Autowired
    VBUserService service;
    @Autowired
    AccountHolderRepository accountHolderRepository;
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    ThirdPartyRepository thirdPartyRepository;
    Address address;

    @BeforeEach
    void setUp() {
        address = new Address().setAdditionalInfo("KJSGD").setCity("Oklahoma").setCountry("India")
                .setStreet("Main street").setStreetNumber(45).setZipCode(8080);

        accountHolderRepository.saveAll(List.of(
                newAccountHolder("Antonio", "aaa").setDateOfBirth(LocalDate.now()).setPrimaryAddress(address),
                newAccountHolder("Antonia", "aab").setDateOfBirth(LocalDate.now()).setPrimaryAddress(address),
                newAccountHolder("Antonino", "aac").setDateOfBirth(LocalDate.now()).setPrimaryAddress(address),
                newAccountHolder("Antoine", "aad").setDateOfBirth(LocalDate.now()).setPrimaryAddress(address)
        ));
        adminRepository.saveAll(List.of(
                newVBAdmin("Antonio", "bbb"),
                newVBAdmin("Antonia", "bba"),
                newVBAdmin("Antonino", "bbc"),
                newVBAdmin("Antoine", "bbd")
        ));
        thirdPartyRepository.saveAll(List.of(
                newThirdParty("Antonio", "abb"),
                newThirdParty("Antonia", "aba"),
                newThirdParty("Antonino", "abc"),
                newThirdParty("Antoine", "abd")
        ));

    }

    @AfterEach
    void tearDown() {
        accountHolderRepository.deleteAll();
        thirdPartyRepository.deleteAll();
        adminRepository.deleteAll();
    }

    @Test
    @DisplayName("Get unknown user by id (OK)")
    void getUnknown_test_ok() throws HttpResponseException, HttpException {
        assertEquals("Antonino", service.getUnknown("abc").getName());
    }

    @Test
    @DisplayName("Get unknown user by id (NOK)")
    void getUnknown_test_nok() {
        int eNum = 0;
        try {
            service.getUnknown("ccc");
        } catch (HttpResponseException e) {
            eNum = e.getStatusCode();
        }

        assertEquals(HttpStatus.NOT_FOUND.value(), eNum);
    }

    @Test
    @DisplayName("Get thirdParty by id (OK)")
    void getThirdParty_test() {
        assertEquals("Antonino", service.getThirdParty("abc").getName());
    }

    @Test
    @DisplayName("Get AccHolder by id (OK)")
    void getAccountHolder_test() {
        assertEquals("Antonio", service.getAccountHolder("aaa").getName());
    }

    @Test
    @DisplayName("Get Admin by id (OK)")
    void getAdmin_test() {
        assertEquals("Antonio", service.getAdmin("bbb").getName());
    }

    @Test
    @DisplayName("Update Existing User")
    void update_test() throws HttpResponseException {
        var dto = new AccountHolderDTO().setName("Patata");
        service.update("aaa", dto);
        assertEquals("Patata", service.getUnknown("aaa").getName());
    }

    @Test
    @DisplayName("Delete user")
    void delete_test() throws HttpResponseException {
        service.delete("aaa");
        assertThrows(HttpResponseException.class, () -> service.getUnknown("aaa"));
    }

    @Test
    @DisplayName("Get All AccountHolders")
    void getAllAccountHolder_test() {
        assertEquals(4, service.getAllAccountHolder().size());
    }

    @Test
    @DisplayName("Get All ThirdParty")
    void getAllThirdParty_test() {
        assertEquals(4, service.getAllThirdParty().size());
    }

    @Test
    @DisplayName("Get All Admin")
    void getAllAdmin_test() {
        assertEquals(4, service.getAllAdmin().size());
    }

    @Test
    @DisplayName("Create new user")
    void create_test() throws HttpResponseException {
        service.create(new AccountHolderDTO("parapapa")
                .setName("piii")
                .setPrimaryAddress(address)
                .setDateOfBirth(LocalDate.now()));
        assertEquals(5, service.getAllAccountHolder().size());
    }

}