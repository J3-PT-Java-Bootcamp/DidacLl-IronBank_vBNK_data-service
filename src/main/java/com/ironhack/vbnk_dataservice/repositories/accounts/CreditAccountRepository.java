package com.ironhack.vbnk_dataservice.repositories.accounts;

import com.ironhack.vbnk_dataservice.data.dao.accounts.CheckingAccount;
import com.ironhack.vbnk_dataservice.data.dao.accounts.CreditAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface CreditAccountRepository extends JpaRepository<CreditAccount, String> {

    List<CheckingAccount> findAllByPrimaryOwnerId(String userId);
    List<CheckingAccount> findAllBySecondaryOwnerId(String userId);
}