package com.ironhack.vbnk_dataservice.data.dto.accounts;

import com.ironhack.vbnk_dataservice.data.AccountStatus;
import com.ironhack.vbnk_dataservice.data.Money;
import com.ironhack.vbnk_dataservice.data.dao.accounts.VBAccount;
import com.ironhack.vbnk_dataservice.data.dao.users.VBAdmin;
import com.ironhack.vbnk_dataservice.data.dao.users.VBUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;
@NoArgsConstructor
@Getter @Setter
public class AccountDTO {
    UUID id;
    Money balance;
    String secretKey;
    VBUser primaryOwner;
    VBUser secondaryOwner;
    AccountStatus status;
    VBAdmin administratedBy;

    public static AccountDTO fromAnyAccountEntity(VBAccount entity){
        return new AccountDTO().setId(entity.getId())
                .setBalance(entity.getBalance())
                .setStatus(entity.getStatus())
                .setSecretKey(entity.getSecretKey())
                .setPrimaryOwner(entity.getPrimaryOwner())
                .setSecondaryOwner(entity.getSecondaryOwner())
                .setAdministratedBy(entity.getAdministratedBy());
    }
}
