package com.ironhack.vbnk_dataservice.data.http.request;

import com.ironhack.vbnk_dataservice.data.dao.accounts.SavingsAccount;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
public class NewSavingsAccountRequest extends NewAccountRequest {

    BigDecimal minimumBalance;
    BigDecimal penaltyFee;
    BigDecimal interestRate;

    public static NewSavingsAccountRequest fromEntity(SavingsAccount entity) {
        var dto = new NewSavingsAccountRequest().setMinimumBalance(entity.getMinimumBalance().getAmount())
                .setPenaltyFee(entity.getPenaltyFee())
                .setInterestRate(entity.getInterestRate());
        dto.setId(entity.getId())
                .setInitialAmount(entity.getBalance().getAmount())
                .setCurrency(entity.getBalance().getCurrency().getCurrencyCode())
                .setStatus(entity.getStatus())
                .setSecretKey(entity.getSecretKey())
                .setPrimaryOwner(entity.getPrimaryOwner().getId())
                .setSecondaryOwner(entity.getSecondaryOwner().getId())
                .setAdministratedBy(entity.getAdministratedBy().getId())
                .setAccountNumber(entity.getAccountNumber());
        return dto;
    }
}
