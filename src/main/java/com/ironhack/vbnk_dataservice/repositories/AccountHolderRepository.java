package com.ironhack.vbnk_dataservice.repositories;

import com.ironhack.vbnk_dataservice.data.dao.users.AccountHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountHolderRepository extends UserRepository, JpaRepository<AccountHolder, String> {
}
