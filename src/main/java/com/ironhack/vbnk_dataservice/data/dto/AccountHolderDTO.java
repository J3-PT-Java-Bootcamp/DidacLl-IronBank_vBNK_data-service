package com.ironhack.vbnk_dataservice.data.dto;

import com.ironhack.vbnk_dataservice.data.Address;
import com.ironhack.vbnk_dataservice.data.dao.AccountHolder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class AccountHolderDTO extends VBUserDTO {
    LocalDate dateOfBirth;
    Address primaryAddress;
    Address mailingAddress;


    public static AccountHolderDTO fromEntity(AccountHolder entity) {
        var retVal = new AccountHolderDTO().setDateOfBirth(entity.getDateOfBirth())
                .setMailingAddress(entity.getMailingAddress())
                .setPrimaryAddress(entity.getPrimaryAddress());
        retVal.setName(entity.getName())
                .setId(entity.getId());
        return retVal;
    }
}
