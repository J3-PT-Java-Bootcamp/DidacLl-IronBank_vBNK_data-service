package com.ironhack.vbnk_dataservice.repositories.users;

import com.ironhack.vbnk_dataservice.data.dao.users.VBAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends com.ironhack.vbnk_dataservice.repositories.users.UserRepository, JpaRepository<VBAdmin, String> {
}
