package com.ironhack.vbnk_dataservice.data.http.request;

import com.ironhack.vbnk_dataservice.data.dao.accounts.CheckingAccount;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
public class NewCheckingAccountRequest extends NewAccountRequest {

    BigDecimal minimumBalance;
    BigDecimal penaltyFee;
    BigDecimal monthlyMaintenanceFee;

    public static NewCheckingAccountRequest fromEntity(CheckingAccount entity) {
        var dto = new NewCheckingAccountRequest().setMinimumBalance(entity.getMinimumBalance().getAmount())
                .setMonthlyMaintenanceFee(entity.getMonthlyMaintenanceFee())
                .setPenaltyFee(entity.getPenaltyFee());
        dto.setId(entity.getId())
                .setAccountNumber(entity.getAccountNumber())
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
