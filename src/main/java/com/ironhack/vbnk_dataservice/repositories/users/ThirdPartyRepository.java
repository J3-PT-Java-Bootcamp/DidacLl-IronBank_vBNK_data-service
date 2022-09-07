package com.ironhack.vbnk_dataservice.repositories.users;

import com.ironhack.vbnk_dataservice.data.dao.users.ThirdParty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThirdPartyRepository extends com.ironhack.vbnk_dataservice.repositories.users.UserRepository, JpaRepository<ThirdParty, String> {
}
