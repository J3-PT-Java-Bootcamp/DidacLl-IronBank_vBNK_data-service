package com.ironhack.vbnk_dataservice.data.dto.accounts;

import com.ironhack.vbnk_dataservice.data.AccountStatus;
import com.ironhack.vbnk_dataservice.data.dao.accounts.StudentCheckingAccount;
import com.ironhack.vbnk_dataservice.data.dao.users.AccountHolder;
import com.ironhack.vbnk_dataservice.data.dao.users.VBAdmin;
import com.ironhack.vbnk_dataservice.data.http.request.NewStudentCheckingAccountRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Currency;

@NoArgsConstructor
@Getter
@Setter
public class StudentCheckingDTO extends AccountDTO {


    public static StudentCheckingDTO fromEntity(StudentCheckingAccount entity) {
        StudentCheckingDTO dto = new StudentCheckingDTO();
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

    public static StudentCheckingDTO fromRequest(NewStudentCheckingAccountRequest request, AccountHolder pOwner, AccountHolder sOwner, VBAdmin admin) {
        StudentCheckingDTO dto = new StudentCheckingDTO();
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
