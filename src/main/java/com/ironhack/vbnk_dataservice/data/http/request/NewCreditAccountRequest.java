package com.ironhack.vbnk_dataservice.data.http.request;

import com.ironhack.vbnk_dataservice.data.dao.accounts.CreditAccount;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
public class NewCreditAccountRequest extends NewAccountRequest {

    BigDecimal creditLimit;
    BigDecimal interestRate;

    public static NewCreditAccountRequest fromEntity(CreditAccount entity) {
        var dto = new NewCreditAccountRequest().setCreditLimit(entity.getCreditLimit().getAmount())
                .setInterestRate(entity.getInterestRate());
        dto.setId(entity.getId())
                .setInitialAmount(entity.getBalance().getAmount())
                .setCurrency(entity.getBalance().getCurrency().getCurrencyCode())
                .setStatus(entity.getStatus())
                .setSecretKey(entity.getSecretKey())
                .setPrimaryOwner(entity.getPrimaryOwner().getId())
                .setSecondaryOwner(entity.getSecondaryOwner().getId())
                .setAdministratedBy(entity.getAdministratedBy().getId());
        return dto;
    }
}
