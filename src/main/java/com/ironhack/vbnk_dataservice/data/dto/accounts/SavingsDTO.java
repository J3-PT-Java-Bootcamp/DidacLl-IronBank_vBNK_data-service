package com.ironhack.vbnk_dataservice.data.dto.accounts;

import com.ironhack.vbnk_dataservice.data.Money;
import com.ironhack.vbnk_dataservice.data.dao.accounts.SavingsAccount;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter @Setter
public class SavingsDTO extends AccountDTO{

    Money minimumBalance;
    BigDecimal penaltyFee;
    BigDecimal interestRate;

    public static SavingsDTO fromEntity(SavingsAccount entity){
        var dto= new SavingsDTO().setMinimumBalance(entity.getMinimumBalance())
                .setPenaltyFee(entity.getPenaltyFee())
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
