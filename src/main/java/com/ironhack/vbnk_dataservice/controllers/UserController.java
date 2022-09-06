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

@RestController
@RequestMapping(path = "/users")
public class UserController {
    @Autowired
    VBUserService service;


    //------------------------------------------------------------------------------GET END POINTS
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<VBUserDTO> get(@RequestParam UUID id) throws HttpException {
        return new ResponseEntity<>(service.getUnknown(id),HttpStatus.FOUND);
    }
    @GetMapping("/admin")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<AdminDTO> getAdmin(@RequestParam UUID id) {
        return new ResponseEntity<>(service.getAdmin(id),HttpStatus.FOUND);
    }
    @GetMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<AccountHolderDTO> getAccountHolder(@RequestParam UUID id) {
        return new ResponseEntity<>(service.getAccountHolder(id),HttpStatus.FOUND);
    }
    @GetMapping("/third-party")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<ThirdPartyDTO> getThirdParty(@RequestParam UUID id)  {
        return new ResponseEntity<>(service.getThirdParty(id),HttpStatus.FOUND);
    }
    //------------------------------------------------------------------------------GET ALL END POINTS

    @GetMapping("/admin/all")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<List<AdminDTO>> getAllAdmin() {
        return new ResponseEntity<>(service.getAllAdmin(),HttpStatus.FOUND);
    }
    @GetMapping("/user/all")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<List<AccountHolderDTO>> getAllAccountHolder() {
        return new ResponseEntity<>(service.getAllAccountHolder(),HttpStatus.FOUND);
    }
    @GetMapping("/third-party/all")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<List<ThirdPartyDTO>> getThirdParty()  {
        return new ResponseEntity<>(service.getAllThirdParty(),HttpStatus.FOUND);
    }

    //------------------------------------------------------------------------------CREATE END POINTS
    @PostMapping("/new/user") // TODO: 06/09/2022 change UUID id for keycloak id passed on create
    @ResponseStatus(HttpStatus.CREATED)
    void createAccountHolder(@RequestBody AccountHolderDTO dto) throws HttpResponseException {
        service.create(dto);
    }
    @PostMapping("/new/admin") // TODO: 06/09/2022 change UUID id for keycloak id passed on create
    @ResponseStatus(HttpStatus.CREATED)
    void createAdmin(@RequestBody AdminDTO dto) throws HttpResponseException {
        service.create(dto);
    }
    @PostMapping("/new/third-party") // TODO: 06/09/2022 change UUID id for keycloak id passed on create
    @ResponseStatus(HttpStatus.CREATED)
    void createThirdParty(@RequestBody ThirdPartyDTO dto) throws HttpResponseException {
        service.create(dto);
    }

    //------------------------------------------------------------------------------UPDATE END POINTS

    @PatchMapping("/update/admin")
    @ResponseStatus(HttpStatus.OK)
    void updateAdmin(@RequestParam UUID id,@RequestBody AdminDTO dto) throws HttpResponseException {
        service.update(id,dto);
    }
    @PatchMapping("/update/user")
    @ResponseStatus(HttpStatus.CREATED)
    void updateAccountHolder(@RequestParam UUID id,@RequestBody AccountHolderDTO dto) throws HttpResponseException {
        service.update(id,dto);
    }

    @PatchMapping("/update/third-party")
    @ResponseStatus(HttpStatus.CREATED)
    void updateThirdParty(@RequestParam UUID id,@RequestBody ThirdPartyDTO dto) throws HttpResponseException {
        service.update(id,dto);
    }

    //------------------------------------------------------------------------------DELETE END POINTS

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    void delete(@RequestParam UUID id) throws  HttpResponseException {
        service.delete(id);
    }



}
