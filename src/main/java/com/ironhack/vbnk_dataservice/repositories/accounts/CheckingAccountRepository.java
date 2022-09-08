package com.ironhack.vbnk_dataservice.repositories.accounts;

import com.ironhack.vbnk_dataservice.data.dao.accounts.CheckingAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckingAccountRepository extends JpaRepository<CheckingAccount, String> {

    List<CheckingAccount> findAllByPrimaryOwnerId(String userId);

    List<CheckingAccount> findAllBySecondaryOwnerId(String userId);
}