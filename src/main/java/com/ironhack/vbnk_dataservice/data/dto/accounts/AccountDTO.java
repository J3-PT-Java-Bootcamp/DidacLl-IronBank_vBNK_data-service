package com.ironhack.vbnk_dataservice.data.dto.accounts;

import com.ironhack.vbnk_dataservice.data.AccountStatus;
import com.ironhack.vbnk_dataservice.data.dao.accounts.VBAccount;
import com.ironhack.vbnk_dataservice.data.dao.users.AccountHolder;
import com.ironhack.vbnk_dataservice.data.dao.users.VBAdmin;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Currency;

@NoArgsConstructor
@Getter
@Setter
public class AccountDTO {
    String id;
    String accountNumber;
    BigDecimal amount;
    Currency currency;
    String secretKey;
    AccountHolder primaryOwner;
    AccountHolder secondaryOwner;
    AccountStatus status;
    VBAdmin administratedBy;

    public static AccountDTO fromAnyAccountEntity(VBAccount entity) {
        return new AccountDTO().setId(entity.getId())
                .setAccountNumber(entity.getAccountNumber())
                .setAmount(entity.getBalance().getAmount())
                .setCurrency(entity.getBalance().getCurrency())
                .setStatus(entity.getStatus())
                .setSecretKey(entity.getSecretKey())
                .setPrimaryOwner(entity.getPrimaryOwner())
                .setSecondaryOwner(entity.getSecondaryOwner())
                .setAdministratedBy(entity.getAdministratedBy());
    }

    public static CreditDTO convertToCreditDTO(AccountDTO accDTO) {
        return (CreditDTO) copyBaseValues(accDTO, new CreditDTO());
    }

    public static CheckingDTO convertToCheckingDTO(AccountDTO accDTO) {
        return (CheckingDTO) copyBaseValues(accDTO, new CheckingDTO());
    }

    public static SavingsDTO convertToSavingsDTO(AccountDTO accDTO) {
        return (SavingsDTO) copyBaseValues(accDTO, new SavingsDTO());
    }

    public static StudentCheckingDTO convertToStudentDTO(AccountDTO accDTO) {
        return (StudentCheckingDTO) copyBaseValues(accDTO, new StudentCheckingDTO());
    }

    private static AccountDTO copyBaseValues(AccountDTO src, AccountDTO dest) {
        return dest.setAccountNumber(src.getAccountNumber())
                .setAmount(src.getAmount()).setCurrency(src.getCurrency())
                .setAdministratedBy(src.administratedBy)
                .setStatus(src.getStatus())
                .setPrimaryOwner(src.getPrimaryOwner())
                .setSecondaryOwner(src.getSecondaryOwner())
                .setSecretKey(src.getSecretKey())
                .setId(src.getId());
    }

}
