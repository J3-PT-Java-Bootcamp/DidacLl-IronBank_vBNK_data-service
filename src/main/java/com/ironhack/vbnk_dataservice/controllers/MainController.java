package com.ironhack.vbnk_dataservice.controllers;

import com.ironhack.vbnk_dataservice.data.dao.accounts.VBAccount;
import com.ironhack.vbnk_dataservice.data.dto.users.AccountHolderDTO;
import com.ironhack.vbnk_dataservice.data.dto.users.AdminDTO;
import com.ironhack.vbnk_dataservice.data.dto.users.ThirdPartyDTO;
import org.apache.http.HttpException;
import org.apache.http.client.HttpResponseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("v1/main")
public interface MainController {
// TODO: 09/09/2022

    //------------------------------------------------------------------------------GET END POINTS
    ResponseEntity<AccountHolderDTO> getLoggedUser(Principal principal) throws HttpException;

    ResponseEntity<AdminDTO> getAccountAdministrator(String accountId);

    ResponseEntity<List<VBAccount>> getOwnAccounts();



    //------------------------------------------------------------------------------CREATE END POINTS
    void createAccountHolder(@RequestBody AccountHolderDTO dto, String id) throws HttpResponseException;

    void createAdmin(@RequestBody AdminDTO dto, String id) throws HttpResponseException;

    void createThirdParty(@RequestBody ThirdPartyDTO dto, String id) throws HttpResponseException;

    //------------------------------------------------------------------------------UPDATE END POINTS
    void updateAdmin(String id, @RequestBody AdminDTO dto) throws HttpResponseException;

    void updateAccountHolder(String id, @RequestBody AccountHolderDTO dto) throws HttpResponseException;

    void updateThirdParty(String id, @RequestBody ThirdPartyDTO dto) throws HttpResponseException;

    //------------------------------------------------------------------------------DELETE END POINTS

    void delete(String id) throws HttpResponseException;

}
