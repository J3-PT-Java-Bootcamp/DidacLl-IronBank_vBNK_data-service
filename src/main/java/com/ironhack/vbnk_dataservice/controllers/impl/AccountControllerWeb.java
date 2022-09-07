package com.ironhack.vbnk_dataservice.controllers.impl;

import com.ironhack.vbnk_dataservice.controllers.AccountController;
import com.ironhack.vbnk_dataservice.data.dto.accounts.AccountDTO;
import com.ironhack.vbnk_dataservice.data.dto.accounts.CheckingDTO;
import com.ironhack.vbnk_dataservice.data.dto.accounts.CreditDTO;
import com.ironhack.vbnk_dataservice.data.dto.accounts.SavingsDTO;
import com.ironhack.vbnk_dataservice.services.VBAccountService;
import org.apache.http.HttpException;
import org.apache.http.client.HttpResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
@RestController
@RequestMapping("v1/accounts")
public class AccountControllerWeb implements AccountController {

    @Autowired
    VBAccountService service;
    @Override
    public ResponseEntity<AccountDTO> getAccount(UUID id) throws HttpException {
        return ResponseEntity.ok(service.getAccount(id));
    }

    @Override
    public ResponseEntity<List<AccountDTO>> getAllUserAccounts(String userId) {
        return ResponseEntity.ok(service.getAllUserAccounts(userId));
    }

    @Override
    public void createSavingsAccount(SavingsDTO dto, String userId) throws HttpResponseException {
        service.create(dto,userId);
    }

    @Override
    public void createChecking(CheckingDTO dto, String userId) throws HttpResponseException {
        service.create(dto,userId);
    }

    @Override
    public void createCreditAccount(CreditDTO dto, String userId) throws HttpResponseException {
        service.create(dto,userId);
    }

    @Override
    public void updateSavingsAccount(SavingsDTO dto, UUID id) throws HttpResponseException {
        service.update(dto,id);
    }

    @Override
    public void updateChecking(CheckingDTO dto, UUID id) throws HttpResponseException {
        service.update(dto,id);
    }

    @Override
    public void updateCreditAccount(CreditDTO dto, UUID id) throws HttpResponseException {
        service.update(dto,id);
    }

    @Override
    public void delete(UUID id) throws HttpResponseException {
        service.delete(id);
    }
}
