package com.ironhack.vbnk_dataservice.services.impl;

import com.ironhack.vbnk_dataservice.data.dao.users.AccountHolder;
import com.ironhack.vbnk_dataservice.data.dao.users.ThirdParty;
import com.ironhack.vbnk_dataservice.data.dao.users.VBAdmin;
import com.ironhack.vbnk_dataservice.data.dao.users.VBUser;
import com.ironhack.vbnk_dataservice.data.dto.users.AccountHolderDTO;
import com.ironhack.vbnk_dataservice.data.dto.users.AdminDTO;
import com.ironhack.vbnk_dataservice.data.dto.users.ThirdPartyDTO;
import com.ironhack.vbnk_dataservice.data.dto.users.VBUserDTO;
import com.ironhack.vbnk_dataservice.repositories.users.AccountHolderRepository;
import com.ironhack.vbnk_dataservice.repositories.users.AdminRepository;
import com.ironhack.vbnk_dataservice.repositories.users.ThirdPartyRepository;
import com.ironhack.vbnk_dataservice.services.VBUserService;
import org.apache.http.client.HttpResponseException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class VBUserServiceImpl implements VBUserService {
    final AccountHolderRepository accountHolderRepository;
    final AdminRepository adminRepository;
    final ThirdPartyRepository thirdPartyRepository;

    public VBUserServiceImpl(AccountHolderRepository accountHolderRepository, AdminRepository adminRepository, ThirdPartyRepository thirdPartyRepository) {
        this.accountHolderRepository = accountHolderRepository;
        this.adminRepository = adminRepository;
        this.thirdPartyRepository = thirdPartyRepository;
    }

    //-------------------------------------------------------------------------------------------------GET METHODS
    @Override
    public VBUserDTO getUnknown(String id) throws HttpResponseException {

        try {
            Optional<? extends VBUser> val = accountHolderRepository.findById(id);
            if (val.isEmpty()) {
                val = adminRepository.findById(id);
                if (val.isEmpty()) return dtoFromEntity(thirdPartyRepository.findById(id).orElseThrow());
            }
            return dtoFromEntity(val.get());
        } catch (NoSuchElementException err) {
            throw new HttpResponseException(HttpStatus.NOT_FOUND.value(), id + " does not match with any existing User");
        }
    }

    @Override
    public ThirdPartyDTO getThirdParty(String id) {
        return ThirdPartyDTO.fromEntity(thirdPartyRepository.findById(id).orElseThrow());
    }

    @Override
    public AccountHolderDTO getAccountHolder(String id) {
        return AccountHolderDTO.fromEntity(accountHolderRepository.findById(id).orElseThrow());
    }

    @Override
    public AdminDTO getAdmin(String id) {
        return AdminDTO.fromEntity(adminRepository.findById(id).orElseThrow());
    }
    //-------------------------------------------------------------------------------------------------Update METHODS

    @Override
    public void update(String id, VBUserDTO dto) throws HttpResponseException {
        if (dto instanceof AccountHolderDTO accDTO) {
            var originalDTO = AccountHolderDTO.fromEntity(accountHolderRepository.findById(id).orElseThrow());
            if (accDTO.getName() != null && !accDTO.getName().equals("")) originalDTO.setName(accDTO.getName());
            if (accDTO.getMailingAddress() != null) originalDTO.setMailingAddress(accDTO.getMailingAddress());
            if (accDTO.getPrimaryAddress() != null) originalDTO.setPrimaryAddress(accDTO.getPrimaryAddress());
            if (accDTO.getDateOfBirth() != null) originalDTO.setDateOfBirth(accDTO.getDateOfBirth());
            accountHolderRepository.save(AccountHolder.fromDTO(originalDTO));
        } else if (dto instanceof AdminDTO adminDTO) {
            var originalDTO = AdminDTO.fromEntity(adminRepository.findById(id).orElseThrow());
            if (adminDTO.getName() != null && !adminDTO.getName().equals("")) originalDTO.setName(adminDTO.getName());
            adminRepository.save(VBAdmin.fromDTO(originalDTO));
        } else if (dto instanceof ThirdPartyDTO thirdPartyDTO) {
            var originalDTO = ThirdPartyDTO.fromEntity(thirdPartyRepository.findById(id).orElseThrow());
            if (thirdPartyDTO.getHashKey() != null && !thirdPartyDTO.getHashKey().equals(""))
                originalDTO.setHashKey(thirdPartyDTO.getHashKey());
            if (thirdPartyDTO.getName() != null && !thirdPartyDTO.getName().equals(""))
                originalDTO.setName(thirdPartyDTO.getName());
            thirdPartyRepository.save(ThirdParty.fromDTO(originalDTO));
        } else
            throw new HttpResponseException(HttpStatus.I_AM_A_TEAPOT.value(), HttpStatus.I_AM_A_TEAPOT.getReasonPhrase());
    }

    //-------------------------------------------------------------------------------------------------DELETE METHODS
    @Override
    public void delete(String id) throws HttpResponseException {
        if (accountHolderRepository.existsById(id)) accountHolderRepository.deleteById(id);
        else if (adminRepository.existsById(id)) adminRepository.deleteById(id);
        else if (thirdPartyRepository.existsById(id)) thirdPartyRepository.deleteById(id);
        else throw new HttpResponseException(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase());
    }

    //-------------------------------------------------------------------------------------------------GetAll METHODS
    @Override
    public List<AccountHolderDTO> getAllAccountHolder() {
        return accountHolderRepository.findAll().stream().map(AccountHolderDTO::fromEntity).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public List<ThirdPartyDTO> getAllThirdParty() {
        return thirdPartyRepository.findAll().stream().map(ThirdPartyDTO::fromEntity).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public List<AdminDTO> getAllAdmin() {
        return adminRepository.findAll().stream().map(AdminDTO::fromEntity).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public AdminDTO getRandomAdmin() {
        Random rand = new Random();
        var list = adminRepository.findAll();
        return AdminDTO.fromEntity(list.get(rand.nextInt(0, list.size())));
    }

    @Override
    public boolean existsById(String id) {
        return accountHolderRepository.existsById(id) || adminRepository.existsById(id) || thirdPartyRepository.existsById(id);
    }

    //-------------------------------------------------------------------------------------------------CREATE METHODS
    @Override
    public VBUserDTO create(VBUserDTO dto) throws HttpResponseException {
        // TODO: 06/09/2022 Implement id from keycloak
        if (dto instanceof AccountHolderDTO)
            return AccountHolderDTO.fromEntity(accountHolderRepository.save(AccountHolder.fromDTO((AccountHolderDTO) dto)));
        else if (dto instanceof AdminDTO)
            return AdminDTO.fromEntity(adminRepository.save(VBAdmin.fromDTO((AdminDTO) dto)));
        else if (dto instanceof ThirdPartyDTO)
            return ThirdPartyDTO.fromEntity(thirdPartyRepository.save(ThirdParty.fromDTO((ThirdPartyDTO) dto)));
        else
            throw new HttpResponseException(HttpStatus.I_AM_A_TEAPOT.value(), HttpStatus.I_AM_A_TEAPOT.getReasonPhrase());
    }


    //-------------------------------------------------------------------------------------------------PRIVATE METHODS
    private VBUserDTO dtoFromEntity(VBUser entity) {
        if (entity instanceof AccountHolder) return AccountHolderDTO.fromEntity((AccountHolder) entity);
        else if (entity instanceof VBAdmin) return AdminDTO.fromEntity((VBAdmin) entity);
        else return ThirdPartyDTO.fromEntity((ThirdParty) entity);
    }

    private VBUser entityFromDTO(VBUserDTO dto) {
        if (dto instanceof AccountHolderDTO) return AccountHolder.fromDTO((AccountHolderDTO) dto);
        else if (dto instanceof AdminDTO) return VBAdmin.fromDTO((AdminDTO) dto);
        else return ThirdParty.fromDTO((ThirdPartyDTO) dto);
    }

//    private UserRepository switchRepository(VBUserDTO dto){
//
//        if(dto instanceof AccountHolderDTO)return accountHolderRepository;
//        else if (dto instanceof AdminDTO) return adminRepository;
//        else return thirdPartyRepository;
//    }


}
