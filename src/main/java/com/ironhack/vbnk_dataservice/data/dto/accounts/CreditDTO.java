package com.ironhack.vbnk_dataservice.data.dto.accounts;

import com.ironhack.vbnk_dataservice.data.Money;
import com.ironhack.vbnk_dataservice.data.dao.accounts.CreditAccount;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter @Setter
public class CreditDTO extends AccountDTO{

    Money creditLimit;
    BigDecimal interestRate;

    public static CreditDTO fromEntity(CreditAccount entity){
        var dto= new CreditDTO().setCreditLimit(entity.getCreditLimit())
                .setInterestRate(entity.getInterestRate());
        dto.setId(entity.getId())
                .setBalance(entity.getBalance())
                .setStatus(entity.getStatus())
                .setSecretKey(entity.getSecretKey())
                .setPrimaryOwner(entity.getPrimaryOwner())
                .setSecondaryOwner(entity.getSecondaryOwner())
                .setAdministratedBy(entity.getAdministratedBy());
        return dto;
    }
}
