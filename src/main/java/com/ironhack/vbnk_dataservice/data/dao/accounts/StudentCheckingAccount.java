package com.ironhack.vbnk_dataservice.data.dao.accounts;

import com.ironhack.vbnk_dataservice.data.dto.accounts.StudentCheckingDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
//@NoArgsConstructor
@Getter
@Setter
public class StudentCheckingAccount extends VBAccount {


    public static StudentCheckingAccount fromDTO(StudentCheckingDTO dto){
        var retVal= new StudentCheckingAccount();
        retVal.setId(dto.getId())
                .setBalance(dto.getBalance())
                .setStatus(dto.getStatus())
                .setSecretKey(dto.getSecretKey())
                .setPrimaryOwner(dto.getPrimaryOwner())
                .setSecondaryOwner(dto.getSecondaryOwner())
                .setAdministratedBy(dto.getAdministratedBy());
        return retVal;
    }

}
