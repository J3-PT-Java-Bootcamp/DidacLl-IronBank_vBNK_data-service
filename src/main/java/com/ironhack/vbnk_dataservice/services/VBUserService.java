package com.ironhack.vbnk_dataservice.services;

import com.ironhack.vbnk_dataservice.data.dto.AccountHolderDTO;
import com.ironhack.vbnk_dataservice.data.dto.AdminDTO;
import com.ironhack.vbnk_dataservice.data.dto.ThirdPartyDTO;
import com.ironhack.vbnk_dataservice.data.dto.VBUserDTO;
import org.apache.http.HttpException;
import org.apache.http.client.HttpResponseException;

import java.util.List;
import java.util.UUID;

public interface VBUserService {

    VBUserDTO getUnknown(UUID id) throws HttpException;

    void create(VBUserDTO dto) throws HttpResponseException;

    ThirdPartyDTO getThirdParty(UUID id);

    AccountHolderDTO getAccountHolder(UUID id);

    AdminDTO getAdmin(UUID id);

    void update(UUID id, VBUserDTO dto) throws HttpResponseException;

    void delete(UUID id) throws HttpResponseException;

    List<AccountHolderDTO> getAllAccountHolder();

    List<ThirdPartyDTO> getAllThirdParty();

    List<AdminDTO> getAllAdmin();
}
