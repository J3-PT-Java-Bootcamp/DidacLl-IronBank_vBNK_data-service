package com.ironhack.vbnk_dataservice.data.dao.accounts;

import com.ironhack.vbnk_dataservice.data.Money;
import com.ironhack.vbnk_dataservice.data.dto.accounts.SavingsDTO;
import com.ironhack.vbnk_dataservice.utils.MoneyConverter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Convert;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
//@NoArgsConstructor
@Getter
@Setter
public class SavingsAccount extends VBAccount {
    private BigDecimal interestRate, penaltyFee;
    @Convert(converter = MoneyConverter.class)
    private Money minimumBalance;

    public static SavingsAccount fromDTO(SavingsDTO dto) {
        var retEntity = new SavingsAccount().setMinimumBalance(new Money(dto.getMinimumBalance(),dto.getCurrency()))
                .setPenaltyFee(dto.getPenaltyFee())
                .setInterestRate(dto.getInterestRate());
        retEntity.setId(dto.getId())
                .setBalance(new Money(dto.getAmount(),dto.getCurrency()))                .setStatus(dto.getStatus())
                .setSecretKey(dto.getSecretKey())
                .setPrimaryOwner(dto.getPrimaryOwner())
                .setSecondaryOwner(dto.getSecondaryOwner())
                .setAdministratedBy(dto.getAdministratedBy());
        return retEntity;
    }
}
