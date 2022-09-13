package com.ironhack.vbnk_dataservice.data.dto.accounts;

import com.ironhack.vbnk_dataservice.data.AccountStatus;
import com.ironhack.vbnk_dataservice.data.dao.accounts.SavingsAccount;
import com.ironhack.vbnk_dataservice.data.dao.users.AccountHolder;
import com.ironhack.vbnk_dataservice.data.dao.users.VBAdmin;
import com.ironhack.vbnk_dataservice.data.http.request.NewSavingsAccountRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Currency;

@NoArgsConstructor
@Getter
@Setter
public class SavingsDTO extends AccountDTO {

    BigDecimal minimumBalance;
    BigDecimal penaltyFee;
    BigDecimal interestRate;

    public static SavingsDTO fromEntity(SavingsAccount entity) {
        SavingsDTO dto = new SavingsDTO().setMinimumBalance(entity.getMinimumBalance().getAmount())
                .setPenaltyFee(entity.getPenaltyFee())
                .setInterestRate(entity.getInterestRate());
        dto.setId(entity.getId())
                .setAmount(entity.getBalance().getAmount())
                .setCurrency(entity.getBalance().getCurrency())
                .setStatus(entity.getStatus())
                .setSecretKey(entity.getSecretKey())
                .setPrimaryOwner(entity.getPrimaryOwner())
                .setSecondaryOwner(entity.getSecondaryOwner())
                .setAdministratedBy(entity.getAdministratedBy())
                .setAccountNumber(entity.getAccountNumber());
        return dto;
    }

    public static SavingsDTO fromRequest(NewSavingsAccountRequest request, AccountHolder pOwner, AccountHolder sOwner, VBAdmin admin) {
        SavingsDTO dto = new SavingsDTO().setMinimumBalance(request.getMinimumBalance())
                .setPenaltyFee(request.getPenaltyFee())
                .setInterestRate(request.getInterestRate());
        dto.setId(request.getId())
                .setAmount(request.getInitialAmount())
                .setCurrency(Currency.getInstance(request.getCurrency()))
                .setStatus(AccountStatus.ACTIVE)
                .setSecretKey(request.getSecretKey())
                .setPrimaryOwner(pOwner)
                .setSecondaryOwner(sOwner)
                .setAdministratedBy(admin)
                .setAccountNumber(request.getAccountNumber());
        return dto;
    }
}
