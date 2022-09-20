package com.ironhack.vbnk_dataservice.data.http.views;

import com.ironhack.vbnk_dataservice.data.dto.accounts.AccountDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter @Setter
@NoArgsConstructor
public class AccountView {
    private String accountNumber,currentBalance,accountType,accountId,accountState;
    private StatementView[] statements;

    public static AccountView fromDTO(AccountDTO dto,StatementView[] statements){
        var view= new AccountView()
                .setCurrentBalance(dto.getAmount().toString()+" "+dto.getCurrency().getDisplayName())
                .setAccountId(dto.getId())
                .setAccountNumber(dto.getAccountNumber())
                .setAccountState(dto.getState().name())
                .setAccountType(dto.getDisplayName())
                .setStatements(statements);
        return view;
    }
}
