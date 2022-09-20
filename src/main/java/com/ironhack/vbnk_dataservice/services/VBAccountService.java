package com.ironhack.vbnk_dataservice.services;

import com.ironhack.vbnk_dataservice.data.dto.accounts.AccountDTO;
import com.ironhack.vbnk_dataservice.data.http.request.NewAccountRequest;
import com.ironhack.vbnk_dataservice.data.http.views.StatementView;
import org.apache.http.client.HttpResponseException;
import org.springframework.security.core.Authentication;

import javax.naming.ServiceUnavailableException;
import java.util.List;

public interface VBAccountService {
    AccountDTO getAccount(String id) throws HttpResponseException;

    List<AccountDTO> getAllUserAccounts(String userId);

    AccountDTO create(NewAccountRequest dto) throws HttpResponseException;

    AccountDTO update(AccountDTO dto, String id) throws HttpResponseException;

    void delete(String id);

    boolean exist(String destinationAccountRef);

    boolean isOwnedBy(AccountDTO acc, String userID);

    boolean isOwnedBy(String accID, String userID) throws HttpResponseException;

    StatementView[] getStatements(int i, String accountRef, Authentication auth) throws ServiceUnavailableException, HttpResponseException;
}
