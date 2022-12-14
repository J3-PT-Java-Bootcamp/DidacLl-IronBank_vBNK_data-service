package com.ironhack.vbnk_dataservice.data.dto.accounts;

import com.ironhack.vbnk_dataservice.data.AccountStatus;
import com.ironhack.vbnk_dataservice.data.dao.accounts.CheckingAccount;
import com.ironhack.vbnk_dataservice.data.dao.users.AccountHolder;
import com.ironhack.vbnk_dataservice.data.dao.users.VBAdmin;
import com.ironhack.vbnk_dataservice.data.http.request.NewCheckingAccountRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Currency;

@NoArgsConstructor
@Getter
@Setter
public class CheckingDTO extends AccountDTO {

    BigDecimal minimumBalance;
    BigDecimal penaltyFee;
    BigDecimal monthlyMaintenanceFee;

    public static CheckingDTO fromEntity(CheckingAccount entity) {
        CheckingDTO dto = new CheckingDTO().setMinimumBalance(entity.getMinimumBalance().getAmount())
                .setMonthlyMaintenanceFee(entity.getMonthlyMaintenanceFee())
                .setPenaltyFee(entity.getPenaltyFee());
        dto.setId(entity.getId())
                .setAccountNumber(entity.getAccountNumber())
                .setAmount(entity.getBalance().getAmount())
                .setCurrency(entity.getBalance().getCurrency())
                .setStatus(entity.getStatus())
                .setSecretKey(entity.getSecretKey())
                .setPrimaryOwner(entity.getPrimaryOwner())
                .setSecondaryOwner(entity.getSecondaryOwner())
                .setAdministratedBy(entity.getAdministratedBy());
        return dto;
    }

    public static CheckingDTO fromRequest(NewCheckingAccountRequest request, AccountHolder pOwner, AccountHolder sOwner, VBAdmin admin) {
        CheckingDTO dto = new CheckingDTO().setMinimumBalance(request.getMinimumBalance())
                .setMonthlyMaintenanceFee(request.getMonthlyMaintenanceFee())
                .setPenaltyFee(request.getPenaltyFee());
        dto.setId(request.getId())
                .setAccountNumber(request.getAccountNumber())
                .setAmount(request.getInitialAmount())
                .setCurrency(Currency.getInstance(request.getCurrency()))
                .setStatus(AccountStatus.ACTIVE)
                .setSecretKey(request.getSecretKey())
                .setPrimaryOwner(pOwner)
                .setSecondaryOwner(sOwner)
                .setAdministratedBy(admin);
        return dto;
    }
}
