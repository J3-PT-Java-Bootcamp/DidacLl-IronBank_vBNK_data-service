package com.ironhack.vbnk_dataservice.services;

import com.ironhack.vbnk_dataservice.data.dto.users.AccountHolderDTO;
import com.ironhack.vbnk_dataservice.data.dto.users.AdminDTO;
import com.ironhack.vbnk_dataservice.data.dto.users.ThirdPartyDTO;
import com.ironhack.vbnk_dataservice.data.dto.users.VBUserDTO;
import org.apache.http.client.HttpResponseException;

import java.util.List;

public interface VBUserService {

    VBUserDTO getUnknown(String id) throws HttpResponseException;

    void create(VBUserDTO dto) throws HttpResponseException;

    ThirdPartyDTO getThirdParty(String id);

    AccountHolderDTO getAccountHolder(String id);

    AdminDTO getAdmin(String id);

    void update(String id, VBUserDTO dto) throws HttpResponseException;

    void delete(String id) throws HttpResponseException;

    List<AccountHolderDTO> getAllAccountHolder();

    List<ThirdPartyDTO> getAllThirdParty();

    List<AdminDTO> getAllAdmin();

    AdminDTO getRandomAdmin();
}
