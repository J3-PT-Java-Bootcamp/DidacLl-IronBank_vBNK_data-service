package com.ironhack.vbnk_dataservice.data.dto.accounts;

import com.ironhack.vbnk_dataservice.data.AccountStatus;
import com.ironhack.vbnk_dataservice.data.dao.accounts.CreditAccount;
import com.ironhack.vbnk_dataservice.data.dao.users.AccountHolder;
import com.ironhack.vbnk_dataservice.data.dao.users.VBAdmin;
import com.ironhack.vbnk_dataservice.data.http.request.NewCreditAccountRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Currency;

@NoArgsConstructor
@Getter
@Setter
public class CreditDTO extends AccountDTO {

    BigDecimal creditLimit;
    BigDecimal interestRate;

    public static CreditDTO fromEntity(CreditAccount entity) {
        CreditDTO dto = new CreditDTO().setCreditLimit(entity.getCreditLimit().getAmount())
                .setInterestRate(entity.getInterestRate());
        dto.setId(entity.getId())
                .setAmount(entity.getBalance().getAmount())
                .setCurrency(entity.getBalance().getCurrency())
                .setStatus(entity.getStatus())
                .setSecretKey(entity.getSecretKey())
                .setPrimaryOwner(entity.getPrimaryOwner())
                .setSecondaryOwner(entity.getSecondaryOwner())
                .setAccountNumber(entity.getAccountNumber())
                .setAdministratedBy(entity.getAdministratedBy());
        return dto;
    }

    public static CreditDTO fromRequest(NewCreditAccountRequest request, AccountHolder pOwner, AccountHolder sOwner, VBAdmin admin) {
        CreditDTO dto = new CreditDTO().setCreditLimit(request.getCreditLimit())
                .setInterestRate(request.getInterestRate());
        dto.setId(request.getId())
                .setAmount(request.getInitialAmount())
                .setCurrency(Currency.getInstance(request.getCurrency()))
                .setStatus(AccountStatus.ACTIVE)
                .setSecretKey(request.getSecretKey())
                .setPrimaryOwner(pOwner)
                .setSecondaryOwner(sOwner)
                .setAccountNumber(request.getAccountNumber())
                .setAdministratedBy(admin);
        return dto;
    }
}
