package com.ironhack.vbnk_dataservice.data.http.request;

import com.ironhack.vbnk_dataservice.data.dao.accounts.StudentCheckingAccount;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class NewStudentCheckingAccountRequest extends NewAccountRequest {


    public static NewStudentCheckingAccountRequest fromEntity(StudentCheckingAccount entity) {
        var dto = new NewStudentCheckingAccountRequest();
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
