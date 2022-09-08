package com.ironhack.vbnk_dataservice.controllers.impl;

import com.ironhack.vbnk_dataservice.controllers.AccountController;
import com.ironhack.vbnk_dataservice.data.AccountStatus;
import com.ironhack.vbnk_dataservice.data.Address;
import com.ironhack.vbnk_dataservice.data.Money;
import com.ironhack.vbnk_dataservice.data.dao.accounts.CheckingAccount;
import com.ironhack.vbnk_dataservice.data.dao.accounts.CreditAccount;
import com.ironhack.vbnk_dataservice.data.dao.accounts.SavingsAccount;
import com.ironhack.vbnk_dataservice.data.dao.accounts.StudentCheckingAccount;
import com.ironhack.vbnk_dataservice.data.dao.users.AccountHolder;
import com.ironhack.vbnk_dataservice.data.dao.users.VBAdmin;
import com.ironhack.vbnk_dataservice.data.dto.accounts.*;
import com.ironhack.vbnk_dataservice.data.dto.users.AccountHolderDTO;
import com.ironhack.vbnk_dataservice.data.dto.users.AdminDTO;
import com.ironhack.vbnk_dataservice.services.VBAccountService;
import com.ironhack.vbnk_dataservice.services.VBUserService;
import org.apache.http.client.HttpResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("v1/accounts")
public class AccountControllerWeb implements AccountController {

    @Autowired
    VBAccountService service;
    @Autowired
    VBUserService userService;
    @GetMapping("/populate")
    void populate() throws HttpResponseException {
        var admin = VBAdmin.fromDTO((AdminDTO) userService.create(new AdminDTO().setName("Super Admin").setId("bbb")));
        var user = AccountHolder.fromDTO((AccountHolderDTO) userService.create(
                AccountHolderDTO.newAccountHolderDTO("Antonio", "aaa")
                        .setDateOfBirth(LocalDate.of(1990, 5, 3))
                        .setPrimaryAddress(new Address().setAdditionalInfo("KJSGD").setCity("Oklahoma").setCountry("India")
                                .setStreet("Main street").setStreetNumber(45).setZipCode(8080)))
        );
        Money money = new Money(BigDecimal.valueOf(10));

        var credit = new CreditAccount().setCreditLimit(money).setInterestRate(BigDecimal.TEN);
        credit.setBalance(money).setStatus(AccountStatus.ACTIVE)
                .setPrimaryOwner(user).setAdministratedBy(admin).setSecretKey("patatas");
        var savings = new SavingsAccount().setInterestRate(BigDecimal.TEN)
                .setMinimumBalance(money).setPenaltyFee(BigDecimal.TEN);
        savings.setBalance(money).setStatus(AccountStatus.ACTIVE)
                .setPrimaryOwner(user).setAdministratedBy(admin).setSecretKey("patatas");
        var checking = new CheckingAccount().setPenaltyFee(BigDecimal.TEN)
                .setMinimumBalance(money).setMonthlyMaintenanceFee(BigDecimal.TEN);
         checking.setBalance(money).setStatus(AccountStatus.ACTIVE)
                .setPrimaryOwner(user).setAdministratedBy(admin).setSecretKey("patatas");
        var student = new StudentCheckingAccount();
        student.setBalance(money).setStatus(AccountStatus.ACTIVE)
                .setPrimaryOwner(user).setAdministratedBy(admin).setSecretKey("patatas");
//        repository.save(checking);
        credit = CreditAccount.fromDTO((CreditDTO) service.create(CreditDTO.fromEntity(credit), "aaa"));
        checking = CheckingAccount.fromDTO((CheckingDTO) service.create(SavingsDTO.fromEntity(savings), "aaa"));
        student = StudentCheckingAccount.fromDTO((StudentCheckingDTO) service.create(StudentCheckingDTO.fromEntity(student), "aaa"));

    }
    @Override
    @GetMapping
    public ResponseEntity<AccountDTO> getAccount(@RequestParam String id) throws HttpResponseException {
        return ResponseEntity.ok(service.getAccount(id));
    }

    @Override
    @GetMapping("/all")
    public ResponseEntity<List<AccountDTO>> getAllUserAccounts(@RequestParam String userId) {
        return ResponseEntity.ok(service.getAllUserAccounts(userId));
    }

    @Override
    @PostMapping("/savings")
    public void createSavingsAccount(@RequestBody SavingsDTO dto,@RequestParam String userId) throws HttpResponseException {
        service.create(dto, userId);
    }

    @Override
    @PostMapping("/checking")
    public void createChecking(@RequestBody CheckingDTO dto,@RequestParam String userId) throws HttpResponseException {
        service.create(dto, userId);
    }

    @Override
    @PostMapping("/credit")
    public void createCreditAccount(@RequestBody CreditDTO dto,@RequestParam String userId) throws HttpResponseException {
        service.create(dto, userId);
    }

    @Override
    @PatchMapping("/savings")
    public void updateSavingsAccount(@RequestBody SavingsDTO dto,@RequestParam  String id) throws HttpResponseException {
        service.update(dto, id);
    }

    @Override
    @PatchMapping("/checking")
    public void updateChecking(@RequestBody CheckingDTO dto,@RequestParam  String id) throws HttpResponseException {
        service.update(dto, id);
    }

    @Override
    @PatchMapping("/credit")
    public void updateCreditAccount(@RequestBody CreditDTO dto,@RequestParam  String id) throws HttpResponseException {
        service.update(dto, id);
    }

    @Override
    @DeleteMapping
    public void delete(@RequestParam String id) throws HttpResponseException {
        service.delete(id);
    }
}
