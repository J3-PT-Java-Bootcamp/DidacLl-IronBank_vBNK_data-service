package com.ironhack.vbnk_dataservice.controllers;

import com.ironhack.vbnk_dataservice.data.dto.accounts.AccountDTO;
import com.ironhack.vbnk_dataservice.data.dto.accounts.CheckingDTO;
import com.ironhack.vbnk_dataservice.data.dto.accounts.CreditDTO;
import com.ironhack.vbnk_dataservice.data.dto.accounts.SavingsDTO;
import com.ironhack.vbnk_dataservice.data.http.request.NewAccountRequest;
import org.apache.http.HttpException;
import org.apache.http.client.HttpResponseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface AccountController {

    //------------------------------------------------------------------------------GET END POINTS
    ResponseEntity<AccountDTO> getAccount(String id) throws HttpException, HttpResponseException;

    //------------------------------------------------------------------------------GET ALL END POINTS

    ResponseEntity<List<AccountDTO>> getAllUserAccounts(String userId);

    //------------------------------------------------------------------------------CREATE END POINTS
    void createSavingsAccount(@RequestBody NewAccountRequest dto, String userId) throws HttpResponseException;

    void createChecking(@RequestBody NewAccountRequest dto, String userId) throws HttpResponseException;

    void createCreditAccount(@RequestBody NewAccountRequest dto, String userId) throws HttpResponseException;

    //------------------------------------------------------------------------------UPDATE END POINTS
    void updateSavingsAccount(@RequestBody SavingsDTO dto, String id) throws HttpResponseException;

    void updateChecking(@RequestBody CheckingDTO dto, String id) throws HttpResponseException;

    void updateCreditAccount(@RequestBody CreditDTO dto, String id) throws HttpResponseException;

    //------------------------------------------------------------------------------DELETE END POINTS

    void delete(String id) throws HttpResponseException;


}
