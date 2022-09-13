package com.ironhack.vbnk_dataservice.services;

import com.ironhack.vbnk_dataservice.data.dto.accounts.AccountDTO;
import com.ironhack.vbnk_dataservice.data.http.request.NewAccountRequest;
import org.apache.http.client.HttpResponseException;

import java.util.List;

public interface VBAccountService {
    AccountDTO getAccount(String id) throws HttpResponseException;

    List<AccountDTO> getAllUserAccounts(String userId);

    AccountDTO create(NewAccountRequest dto, String userId) throws HttpResponseException;

    void update(AccountDTO dto, String id) throws HttpResponseException;

    void delete(String id);

    boolean exist(String destinationAccountRef);
}
