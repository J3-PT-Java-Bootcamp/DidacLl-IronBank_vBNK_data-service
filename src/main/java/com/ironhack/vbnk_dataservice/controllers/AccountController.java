package com.ironhack.vbnk_dataservice.controllers;

import com.ironhack.vbnk_dataservice.data.dto.accounts.AccountDTO;
import com.ironhack.vbnk_dataservice.data.dto.accounts.CheckingDTO;
import com.ironhack.vbnk_dataservice.data.dto.accounts.CreditDTO;
import com.ironhack.vbnk_dataservice.data.dto.accounts.SavingsDTO;
import com.ironhack.vbnk_dataservice.data.dto.users.AccountHolderDTO;
import com.ironhack.vbnk_dataservice.data.dto.users.AdminDTO;
import com.ironhack.vbnk_dataservice.data.dto.users.ThirdPartyDTO;
import org.apache.http.HttpException;
import org.apache.http.client.HttpResponseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

public interface AccountController {

    //------------------------------------------------------------------------------GET END POINTS
    ResponseEntity<AccountDTO> getAccount(UUID id) throws HttpException;
    
    //------------------------------------------------------------------------------GET ALL END POINTS

    ResponseEntity<List<AccountDTO>> getAllUserAccounts(String userId);

    //------------------------------------------------------------------------------CREATE END POINTS
    void createSavingsAccount(@RequestBody SavingsDTO dto, String userId) throws HttpResponseException;

    void createChecking(@RequestBody CheckingDTO dto, String userId) throws HttpResponseException;

    void createCreditAccount(@RequestBody CreditDTO dto, String userId) throws HttpResponseException;
    //------------------------------------------------------------------------------UPDATE END POINTS
    void updateSavingsAccount(@RequestBody SavingsDTO dto, UUID id) throws HttpResponseException;

    void updateChecking(@RequestBody CheckingDTO dto, UUID id) throws HttpResponseException;

    void updateCreditAccount(@RequestBody CreditDTO dto, UUID id) throws HttpResponseException;

    //------------------------------------------------------------------------------DELETE END POINTS

    void delete(UUID id) throws HttpResponseException;


}
