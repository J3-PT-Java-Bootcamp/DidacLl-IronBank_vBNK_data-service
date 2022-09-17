package com.ironhack.vbnk_dataservice.data.dto.accounts;

import com.ironhack.vbnk_dataservice.data.AccountState;
import com.ironhack.vbnk_dataservice.data.dao.accounts.SavingsAccount;
import com.ironhack.vbnk_dataservice.data.dao.accounts.VBAccount;
import com.ironhack.vbnk_dataservice.data.dao.users.AccountHolder;
import com.ironhack.vbnk_dataservice.data.dao.users.VBAdmin;
import com.ironhack.vbnk_dataservice.data.http.request.NewSavingsAccountRequest;
import com.ironhack.vbnk_dataservice.utils.VBNKConfig;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Currency;

import static com.ironhack.vbnk_dataservice.utils.VBNKConfig.*;

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
                .setState(entity.getState())
                .setSecretKey(entity.getSecretKey())
                .setPrimaryOwner(entity.getPrimaryOwner())
                .setSecondaryOwner(entity.getSecondaryOwner())
                .setAdministratedBy(entity.getAdministratedBy())
                .setAccountNumber(entity.getAccountNumber());
        return dto;
    }

    public static SavingsDTO fromRequest(NewSavingsAccountRequest request, AccountHolder pOwner, AccountHolder sOwner, VBAdmin admin) {
        BigDecimal minBal = request.getMinimumBalance();
        BigDecimal rate = request.getInterestRate();
        SavingsDTO dto = new SavingsDTO();
        if(minBal !=null&&minBal.compareTo(new BigDecimal(VBNK_MIN_SAVINGS_MINIMUM_BALANCE))>=0&&
        minBal.compareTo(new BigDecimal(VBNK_MAX_SAVINGS_MINIMUM_BALANCE))<1)dto.setMinimumBalance(minBal);
        if(rate !=null&&rate.compareTo(new BigDecimal(VBNK_MIN_SAVINGS_INTEREST_RATE))>=0&&
        rate.compareTo(new BigDecimal(VBNK_MAX_SAVINGS_INTEREST_RATE))<1)dto.setInterestRate(rate);
        dto
                .setAmount(request.getInitialAmount())
                .setCurrency(Currency.getInstance(request.getCurrency()))
                .setState(AccountState.ACTIVE)
                .setSecretKey(request.getSecretKey())
                .setPrimaryOwner(pOwner)
                .setSecondaryOwner(sOwner)
                .setAdministratedBy(admin)
                .setAccountNumber(request.getAccountNumber());
        return dto;
    }
}
