package com.ironhack.vbnk_dataservice.data.dao.accounts;

import com.ironhack.vbnk_dataservice.data.Money;
import com.ironhack.vbnk_dataservice.data.dto.accounts.CheckingDTO;
import com.ironhack.vbnk_dataservice.utils.MoneyConverter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Convert;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class CheckingAccount extends VBAccount {
    @Convert(converter = MoneyConverter.class)
    Money minimumBalance;
    BigDecimal penaltyFee;
    BigDecimal monthlyMaintenanceFee;

    public static CheckingAccount fromDTO(CheckingDTO dto) {
        var entity = new CheckingAccount().setMinimumBalance(new Money(dto.getMinimumBalance(),dto.getCurrency()))
                .setMonthlyMaintenanceFee(dto.getMonthlyMaintenanceFee())
                .setPenaltyFee(dto.getPenaltyFee());
        entity.setId(dto.getId())
                .setAccountNumber(dto.getAccountNumber())
                .setBalance(new Money(dto.getAmount(),dto.getCurrency()))                .setStatus(dto.getStatus())
                .setSecretKey(dto.getSecretKey())
                .setPrimaryOwner(dto.getPrimaryOwner())
                .setSecondaryOwner(dto.getSecondaryOwner())
                .setAdministratedBy(dto.getAdministratedBy());
        return entity;
    }

}
