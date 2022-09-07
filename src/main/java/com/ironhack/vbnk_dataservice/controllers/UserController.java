package com.ironhack.vbnk_dataservice.controllers;

import com.ironhack.vbnk_dataservice.data.dto.AccountHolderDTO;
import com.ironhack.vbnk_dataservice.data.dto.AdminDTO;
import com.ironhack.vbnk_dataservice.data.dto.ThirdPartyDTO;
import com.ironhack.vbnk_dataservice.data.dto.VBUserDTO;
import org.apache.http.HttpException;
import org.apache.http.client.HttpResponseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserController {

    //------------------------------------------------------------------------------GET END POINTS
    ResponseEntity<VBUserDTO> get(String id) throws HttpException;

    ResponseEntity<AdminDTO> getAdmin(String id);

    ResponseEntity<AccountHolderDTO> getAccountHolder(String id);

    ResponseEntity<ThirdPartyDTO> getThirdParty(String id);
    //------------------------------------------------------------------------------GET ALL END POINTS

    ResponseEntity<List<AdminDTO>> getAllAdmin();

    ResponseEntity<List<AccountHolderDTO>> getAllAccountHolder();

    ResponseEntity<List<ThirdPartyDTO>> getThirdParty();

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
