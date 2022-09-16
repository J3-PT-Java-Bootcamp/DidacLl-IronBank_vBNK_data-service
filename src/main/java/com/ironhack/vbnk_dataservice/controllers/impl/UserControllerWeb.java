package com.ironhack.vbnk_dataservice.controllers.impl;

import com.ironhack.vbnk_dataservice.data.Address;
import com.ironhack.vbnk_dataservice.data.dto.users.AccountHolderDTO;
import com.ironhack.vbnk_dataservice.data.dto.users.AdminDTO;
import com.ironhack.vbnk_dataservice.data.dto.users.ThirdPartyDTO;
import com.ironhack.vbnk_dataservice.data.dto.users.VBUserDTO;
import com.ironhack.vbnk_dataservice.data.http.request.NewAccountHolderRequest;
import com.ironhack.vbnk_dataservice.data.http.request.NewAdminRequest;
import com.ironhack.vbnk_dataservice.repositories.users.AccountHolderRepository;
import com.ironhack.vbnk_dataservice.services.VBUserService;
import org.apache.http.client.HttpResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static com.ironhack.vbnk_dataservice.data.dao.users.AccountHolder.newAccountHolder;

@RestController
@RequestMapping(path = "/v1/data")
public class UserControllerWeb {
    @Autowired
    VBUserService service;
    @Autowired
    AccountHolderRepository repo;


    @GetMapping("/dev/users/populate")
    void populate() {
        var address = new Address().setAdditionalInfo("KJSGD").setCity("Oklahoma").setCountry("India")
                .setStreet("Main street").setStreetNumber(45).setZipCode(8080);

        repo.saveAll(List.of(
                newAccountHolder("Antonio", "aaa","Antonio","Antoniez").setDateOfBirth(LocalDate.now()).setPrimaryAddress(address),
                newAccountHolder("Antonia", "aab","Antonio","Antoniez").setDateOfBirth(LocalDate.now()).setPrimaryAddress(address),
                newAccountHolder("Antonino", "aac","Antonio","Antoniez").setDateOfBirth(LocalDate.now()).setPrimaryAddress(address),
                newAccountHolder("Antoine", "aad","Antonio","Antoniez").setDateOfBirth(LocalDate.now()).setPrimaryAddress(address)
        ));
    }

    //------------------------------------------------------------------------------GET END POINTS
    @GetMapping("/client/test/{ping}")
    @ResponseStatus(HttpStatus.OK)
    String ping(@PathVariable("ping") String ping) {
        return ping.replace('i', 'o');
    }

    @GetMapping("/auth/users")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<VBUserDTO> get(@RequestParam String id) throws HttpResponseException {
        return new ResponseEntity<>(service.getUnknown(id), HttpStatus.FOUND);
    }

    @GetMapping("/dev/users/admin")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<AdminDTO> getAdmin(@RequestParam String id) {
        return new ResponseEntity<>(service.getAdmin(id), HttpStatus.FOUND);
    }

    @GetMapping("/auth/users/account-holder")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<AccountHolderDTO> getAccountHolder(@RequestParam String id) {
        return new ResponseEntity<>(service.getAccountHolder(id), HttpStatus.FOUND);
    }

    @GetMapping("/auth/users/third-party")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<ThirdPartyDTO> getThirdParty(@RequestParam String id) {
        return new ResponseEntity<>(service.getThirdParty(id), HttpStatus.FOUND);
    }
    //------------------------------------------------------------------------------GET ALL END POINTS

    @GetMapping("/dev/users/admin/all")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<List<AdminDTO>> getAllAdmin() {
        return new ResponseEntity<>(service.getAllAdmin(), HttpStatus.FOUND);
    }

    @GetMapping("/dev/users/account-holder/all")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<List<AccountHolderDTO>> getAllAccountHolder() {
        return new ResponseEntity<>(service.getAllAccountHolder(), HttpStatus.FOUND);
    }

    @GetMapping("/dev/users/third-party/all")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<List<ThirdPartyDTO>> getThirdParty() {
        return new ResponseEntity<>(service.getAllThirdParty(), HttpStatus.FOUND);
    }

    //------------------------------------------------------------------------------CREATE END POINTS
    @PostMapping("/client/users/new/account-holder")
    @ResponseStatus(HttpStatus.CREATED)
    String createAccountHolder(@RequestBody NewAccountHolderRequest request) throws HttpResponseException {
        if(service.existsById(request.getId())||service.existsByUsername(request.getUsername()))throw new HttpResponseException(409,"User already exists" );
        return service.create(AccountHolderDTO.fromRequest(request)).getId();
    }

    @PostMapping("/client/users/new/admin")
    @ResponseStatus(HttpStatus.CREATED)
    String createAdmin(@RequestBody NewAdminRequest request) throws HttpResponseException {
        if(service.existsById(request.getId())||service.existsByUsername(request.getUserName()))throw new HttpResponseException(409,"User already exists" );
        AdminDTO dto = AdminDTO.fromRequest(request);
        return service.create(dto).getId();
    }

    @PostMapping("/auth/users/new/third-party")
    @ResponseStatus(HttpStatus.CREATED)
    void createThirdParty(@RequestBody ThirdPartyDTO dto, @RequestParam String id) throws HttpResponseException {
        dto.setId(id);
        service.create(dto);
    }

    //------------------------------------------------------------------------------UPDATE END POINTS

    @PatchMapping("/dev/users/update/admin")
    @ResponseStatus(HttpStatus.OK)
    void updateAdmin(@RequestParam String id, @RequestBody AdminDTO dto) throws HttpResponseException {
        service.update(id, dto);
    }

    @PatchMapping("/auth/users/update/account-holder")
    @ResponseStatus(HttpStatus.CREATED)
    void updateAccountHolder(@RequestParam String id, @RequestBody AccountHolderDTO dto) throws HttpResponseException {
        service.update(id, dto);
    }

    @PatchMapping("/auth/users/update/third-party")
    @ResponseStatus(HttpStatus.CREATED)
    void updateThirdParty(@RequestParam String id, @RequestBody ThirdPartyDTO dto) throws HttpResponseException {
        service.update(id, dto);
    }

    //------------------------------------------------------------------------------DELETE END POINTS

    @DeleteMapping("/dev/users/delete")
    @ResponseStatus(HttpStatus.OK)
    void delete(@RequestParam String id) throws HttpResponseException {
        service.delete(id);
    }


}
