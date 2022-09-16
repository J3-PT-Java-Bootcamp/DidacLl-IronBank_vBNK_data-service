package com.ironhack.vbnk_dataservice.controllers.impl;

import com.ironhack.vbnk_dataservice.controllers.AccountController;
import com.ironhack.vbnk_dataservice.data.dto.accounts.AccountDTO;
import com.ironhack.vbnk_dataservice.data.dto.accounts.CheckingDTO;
import com.ironhack.vbnk_dataservice.data.dto.accounts.CreditDTO;
import com.ironhack.vbnk_dataservice.data.dto.accounts.SavingsDTO;
import com.ironhack.vbnk_dataservice.data.http.request.NewAccountRequest;
import com.ironhack.vbnk_dataservice.services.VBAccountService;
import com.ironhack.vbnk_dataservice.services.VBUserService;
import org.apache.http.auth.Credentials;
import org.apache.http.client.HttpResponseException;
import org.keycloak.adapters.RefreshableKeycloakSecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("v1/data")
public class AccountControllerWeb implements AccountController {

    @Autowired
    VBAccountService service;
    @Autowired
    VBUserService userService;

    //    @GetMapping("/dev/accounts/populate")
//    void populate() throws HttpResponseException {
//        var admin = VBAdmin.fromDTO((AdminDTO) userService.create(new AdminDTO().setName("Super Admin").setId("bbb")));
//        var user = AccountHolder.fromDTO((AccountHolderDTO) userService.create(
//                AccountHolderDTO.newAccountHolderDTO("Antonio", "aaa")
//                        .setDateOfBirth(LocalDate.of(1990, 5, 3))
//                        .setPrimaryAddress(new Address().setAdditionalInfo("KJSGD").setCity("Oklahoma").setCountry("India")
//                                .setStreet("Main street").setStreetNumber(45).setZipCode(8080)))
//        );
//        Money money = new Money(BigDecimal.valueOf(10));
//
//        var credit = new CreditAccount().setCreditLimit(money).setInterestRate(BigDecimal.TEN);
//        credit.setBalance(money).setStatus(AccountStatus.ACTIVE)
//                .setPrimaryOwner(user).setAdministratedBy(admin).setSecretKey("patatas");
//        var savings = new SavingsAccount().setInterestRate(BigDecimal.TEN)
//                .setMinimumBalance(money).setPenaltyFee(BigDecimal.TEN);
//        savings.setBalance(money).setStatus(AccountStatus.ACTIVE)
//                .setPrimaryOwner(user).setAdministratedBy(admin).setSecretKey("patatas");
//        var checking = new CheckingAccount().setPenaltyFee(BigDecimal.TEN)
//                .setMinimumBalance(money).setMonthlyMaintenanceFee(BigDecimal.TEN);
//         checking.setBalance(money).setStatus(AccountStatus.ACTIVE)
//                .setPrimaryOwner(user).setAdministratedBy(admin).setSecretKey("patatas");
//        var student = new StudentCheckingAccount();
//        student.setBalance(money).setStatus(AccountStatus.ACTIVE)
//                .setPrimaryOwner(user).setAdministratedBy(admin).setSecretKey("patatas");
////        repository.save(checking);
//        credit = CreditAccount.fromDTO((CreditDTO) service.create(CreditDTO.fromEntity(credit), "aaa"));
//        checking = CheckingAccount.fromDTO((CheckingDTO) service.create(SavingsDTO.fromEntity(savings), "aaa"));
//        student = StudentCheckingAccount.fromDTO((StudentCheckingDTO) service.create(StudentCheckingDTO.fromEntity(student), "aaa"));
//
//    }
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
    public void createSavingsAccount(Authentication auth,@RequestBody NewAccountRequest request, @RequestParam String userId) throws HttpResponseException {
        var admin= userService.getAdmin(((UserDetails)(auth.getDetails())).getUsername());
        service.create(request, userId,admin.getId());
    }

    @Override
    @PostMapping("/auth/accounts/checking")
    public void createChecking(Authentication auth,@RequestBody NewAccountRequest request, @RequestParam String userId) throws HttpResponseException {
        var admin= userService.getAdmin(((UserDetails)(auth.getDetails())).getUsername());
        service.create(request, userId,admin.getId());
    }

    @Override
    @PostMapping("/auth/accounts/credit")
    public void createCreditAccount(Authentication auth,@RequestBody NewAccountRequest request, @RequestParam String userId) throws HttpResponseException {
        var admin= userService.getAdmin(((UserDetails)(auth.getDetails())).getUsername());
        service.create(request, userId,admin.getId());
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
        service.delete(id);
    }
}
