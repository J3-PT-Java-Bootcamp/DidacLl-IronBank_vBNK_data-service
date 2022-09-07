package com.ironhack.vbnk_dataservice.data.dto.accounts;

import com.ironhack.vbnk_dataservice.data.Money;
import com.ironhack.vbnk_dataservice.data.dao.accounts.CheckingAccount;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter @Setter
public class CheckingDTO extends AccountDTO{

    Money minimumBalance;
    BigDecimal penaltyFee;
    BigDecimal monthlyMaintenanceFee;

    public static CheckingDTO fromEntity(CheckingAccount entity){
        var dto= new CheckingDTO().setMinimumBalance(entity.getMinimumBalance())
                .setMonthlyMaintenanceFee(entity.getMonthlyMaintenanceFee())
                .setPenaltyFee(entity.getPenaltyFee());
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
