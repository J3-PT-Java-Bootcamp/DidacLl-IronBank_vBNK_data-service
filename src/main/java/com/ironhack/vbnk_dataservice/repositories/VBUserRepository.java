package com.ironhack.vbnk_dataservice.repositories;

import com.ironhack.vbnk_dataservice.data.dao.VBUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VBUserRepository extends JpaRepository<VBUser, UUID> {
}
