package com.ironhack.vbnk_dataservice.repositories.accounts;

import com.ironhack.vbnk_dataservice.data.dao.accounts.SavingsAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface SavingsAccountRepository extends JpaRepository<SavingsAccount, UUID> {
}