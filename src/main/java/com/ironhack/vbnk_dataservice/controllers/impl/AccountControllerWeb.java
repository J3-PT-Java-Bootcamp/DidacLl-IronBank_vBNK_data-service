package com.ironhack.vbnk_dataservice.controllers.impl;

import com.ironhack.vbnk_dataservice.controllers.AccountController;
import com.ironhack.vbnk_dataservice.data.dto.accounts.AccountDTO;
import com.ironhack.vbnk_dataservice.data.dto.accounts.CheckingDTO;
import com.ironhack.vbnk_dataservice.data.dto.accounts.CreditDTO;
import com.ironhack.vbnk_dataservice.data.dto.accounts.SavingsDTO;
import org.apache.http.HttpException;
import org.apache.http.client.HttpResponseException;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public class AccountControllerWeb implements AccountController {
    @Override
    public ResponseEntity<AccountDTO> getAccount(UUID id) throws HttpException {
        return null;
    }

    @Override
    public ResponseEntity<List<AccountDTO>> getAllUserAccounts(String userId) {
        return null;
    }

    @Override
    public void createSavingsAccount(SavingsDTO dto, String userId) throws HttpResponseException {

    }

    @Override
    public void createChecking(CheckingDTO dto, String userId) throws HttpResponseException {

    }

    @Override
    public void createCreditAccount(CreditDTO dto, String userId) throws HttpResponseException {

    }

    @Override
    public void updateSavingsAccount(SavingsDTO dto, UUID id) throws HttpResponseException {

    }

    @Override
    public void updateChecking(CheckingDTO dto, UUID id) throws HttpResponseException {

    }

    @Override
    public void updateCreditAccount(CreditDTO dto, UUID id) throws HttpResponseException {

    }

    @Override
    public void delete(UUID id) throws HttpResponseException {

    }
}
