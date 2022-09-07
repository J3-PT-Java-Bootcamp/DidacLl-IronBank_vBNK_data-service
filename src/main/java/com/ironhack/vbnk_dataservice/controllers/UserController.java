package com.ironhack.vbnk_dataservice.controllers;

import com.ironhack.vbnk_dataservice.data.dto.AccountHolderDTO;
import com.ironhack.vbnk_dataservice.data.dto.AdminDTO;
import com.ironhack.vbnk_dataservice.data.dto.ThirdPartyDTO;
import com.ironhack.vbnk_dataservice.data.dto.VBUserDTO;
import com.ironhack.vbnk_dataservice.services.VBUserService;
import org.apache.http.HttpException;
import org.apache.http.client.HttpResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

public interface UserController {

    //------------------------------------------------------------------------------GET END POINTS
    ResponseEntity<VBUserDTO> get(@RequestParam UUID id) throws HttpException;
    ResponseEntity<AdminDTO> getAdmin(@RequestParam UUID id);
    ResponseEntity<AccountHolderDTO> getAccountHolder(@RequestParam UUID id);
    ResponseEntity<ThirdPartyDTO> getThirdParty(@RequestParam UUID id);
    //------------------------------------------------------------------------------GET ALL END POINTS

    ResponseEntity<List<AdminDTO>> getAllAdmin();
    ResponseEntity<List<AccountHolderDTO>> getAllAccountHolder() ;
    ResponseEntity<List<ThirdPartyDTO>> getThirdParty();

    //------------------------------------------------------------------------------CREATE END POINTS
    void createAccountHolder(@RequestBody AccountHolderDTO dto,@RequestParam String id) throws HttpResponseException;
    void createAdmin(@RequestBody AdminDTO dto,@RequestParam String id) throws HttpResponseException ;
    void createThirdParty(@RequestBody ThirdPartyDTO dto,@RequestParam String id) throws HttpResponseException ;

    //------------------------------------------------------------------------------UPDATE END POINTS
    void updateAdmin(@RequestParam UUID id,@RequestBody AdminDTO dto) throws HttpResponseException;
    void updateAccountHolder(@RequestParam UUID id,@RequestBody AccountHolderDTO dto) throws HttpResponseException ;
    void updateThirdParty(@RequestParam UUID id,@RequestBody ThirdPartyDTO dto) throws HttpResponseException;

    //------------------------------------------------------------------------------DELETE END POINTS

    void delete(@RequestParam UUID id) throws  HttpResponseException;



}
