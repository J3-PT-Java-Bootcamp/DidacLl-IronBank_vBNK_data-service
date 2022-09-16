package com.ironhack.vbnk_dataservice.controllers.impl;

import com.ironhack.vbnk_dataservice.controllers.AccountController;
import com.ironhack.vbnk_dataservice.data.dto.accounts.AccountDTO;
import com.ironhack.vbnk_dataservice.data.dto.accounts.CheckingDTO;
import com.ironhack.vbnk_dataservice.data.dto.accounts.CreditDTO;
import com.ironhack.vbnk_dataservice.data.dto.accounts.SavingsDTO;
import com.ironhack.vbnk_dataservice.data.http.request.NewAccountRequest;
import com.ironhack.vbnk_dataservice.services.VBAccountService;
import com.ironhack.vbnk_dataservice.services.VBUserService;
import org.apache.http.client.HttpResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("v1/data")
public class AccountControllerWeb implements AccountController {

    @Autowired
    VBAccountService service;
    @Autowired
    VBUserService userService;


    @Override
    @GetMapping("/auth/accounts")
    public ResponseEntity<AccountDTO> getAccount(@RequestParam String id) throws HttpResponseException {
        return ResponseEntity.ok(service.getAccount(id));
    }

    @Override
    @GetMapping("/auth/accounts/all")
    public ResponseEntity<List<AccountDTO>> getAllUserAccounts(@RequestParam String userId) {
        return ResponseEntity.ok(service.getAllUserAccounts(userId));
    }

    @Override
    @PostMapping("/auth/accounts/savings")
    public String createSavingsAccount(Authentication auth, @RequestBody NewAccountRequest request) throws HttpResponseException {
        request.setAdministratedBy( userService.getAdmin(((UserDetails)(auth.getDetails())).getUsername()).getId());
        if(!(userService.existsById(request.getPrimaryOwner())&& userService.existsById(request.getAdministratedBy())))throw new HttpResponseException(404,"USERS NOT FOUND");
        return service.create(request).getAccountNumber();
    }

    @Override
    @PostMapping("/auth/accounts/checking")
    public String createChecking(Authentication auth, @RequestBody NewAccountRequest request) throws HttpResponseException {
        var admin= userService.getAdmin(((UserDetails)(auth.getDetails())).getUsername());
        if(!(userService.existsById(request.getPrimaryOwner())&& userService.existsById(request.getAdministratedBy())))throw new HttpResponseException(404,"USERS NOT FOUND");
        return service.create(request).getAccountNumber();    }

    @Override
    @PostMapping("/auth/accounts/credit")
    public String createCreditAccount(Authentication auth, @RequestBody NewAccountRequest request) throws HttpResponseException {
      request.setAdministratedBy(userService.getAdmin(((UserDetails)(auth.getDetails())).getUsername()).getId());
        if(!(userService.existsById(request.getPrimaryOwner())&& userService.existsById(request.getAdministratedBy())))throw new HttpResponseException(404,"USERS NOT FOUND");
        return service.create(request).getAccountNumber();
    }

    @Override
    @PatchMapping("/auth/accounts/savings")
    public void updateSavingsAccount(@RequestBody SavingsDTO dto, @RequestParam String id) throws HttpResponseException {
        service.update(dto, id);
    }
    @Override
    @PatchMapping("/auth/accounts/checking")
    public void updateChecking(@RequestBody CheckingDTO dto,@RequestParam  String id) throws HttpResponseException {
        service.update(dto, id);
    }
    @Override
    @PatchMapping("/auth/accounts/credit")
    public void updateCreditAccount(@RequestBody CreditDTO dto,@RequestParam  String id) throws HttpResponseException {
        service.update(dto, id);
    }
    @Override
    @DeleteMapping("/auth/accounts")
    public void delete(@RequestParam String id) throws HttpResponseException {
        if(service.getAccount(id).getAmount().compareTo(BigDecimal.ZERO)!=0)throw new HttpResponseException(HttpStatus.CONFLICT.value(), "Account must be at 0 to delete it");
        service.delete(id);
    }
}
