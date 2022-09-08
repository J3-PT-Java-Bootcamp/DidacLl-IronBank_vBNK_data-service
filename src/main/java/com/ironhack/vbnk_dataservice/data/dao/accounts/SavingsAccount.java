package com.ironhack.vbnk_dataservice.data.dao.accounts;

import com.ironhack.vbnk_dataservice.data.Money;
import com.ironhack.vbnk_dataservice.data.dto.accounts.SavingsDTO;
import com.ironhack.vbnk_dataservice.utils.MoneyConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Convert;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
//@NoArgsConstructor
@Getter
@Setter
public class SavingsAccount extends VBAccount {
    BigDecimal interestRate, penaltyFee;
    @Convert(converter = MoneyConverter.class)
    Money minimumBalance;
    public static SavingsAccount fromDTO(SavingsDTO entity){
        var retEntity= new SavingsAccount().setMinimumBalance(entity.getMinimumBalance())
                .setPenaltyFee(entity.getPenaltyFee())
                .setInterestRate(entity.getInterestRate());
        retEntity.setId(entity.getId())
                .setBalance(entity.getBalance())
                .setStatus(entity.getStatus())
                .setSecretKey(entity.getSecretKey())
                .setPrimaryOwner(entity.getPrimaryOwner())
                .setSecondaryOwner(entity.getSecondaryOwner())
                .setAdministratedBy(entity.getAdministratedBy());
        return retEntity;
    }
}
