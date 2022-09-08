package com.ironhack.vbnk_dataservice.data.dto.accounts;

import com.ironhack.vbnk_dataservice.data.Money;
import com.ironhack.vbnk_dataservice.data.dao.accounts.CreditAccount;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
public class CreditDTO extends AccountDTO {

    BigDecimal creditLimit;
    BigDecimal interestRate;

    public static CreditDTO fromEntity(CreditAccount entity) {
        var dto = new CreditDTO().setCreditLimit(entity.getCreditLimit().getAmount())
                .setInterestRate(entity.getInterestRate());
        dto.setId(entity.getId())
                .setAmount(entity.getBalance().getAmount())
                .setCurrency(entity.getBalance().getCurrency())
                .setStatus(entity.getStatus())
                .setSecretKey(entity.getSecretKey())
                .setPrimaryOwner(entity.getPrimaryOwner())
                .setSecondaryOwner(entity.getSecondaryOwner())
                .setAdministratedBy(entity.getAdministratedBy());
        return dto;
    }
}
