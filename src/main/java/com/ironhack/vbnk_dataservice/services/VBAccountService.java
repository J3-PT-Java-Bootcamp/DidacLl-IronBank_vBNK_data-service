package com.ironhack.vbnk_dataservice.services;

import com.ironhack.vbnk_dataservice.data.dto.accounts.AccountDTO;
import com.ironhack.vbnk_dataservice.data.dto.accounts.SavingsDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

public interface VBAccountService {
    AccountDTO getAccount(UUID id);

    List<AccountDTO> getAllUserAccounts(String userId);

    void create(AccountDTO dto, String userId);

    void update(AccountDTO dto, UUID id);

    void delete(UUID id);
}
