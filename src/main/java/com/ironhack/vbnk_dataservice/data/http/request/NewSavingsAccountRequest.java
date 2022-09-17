package com.ironhack.vbnk_dataservice.data.http.request;

import com.ironhack.vbnk_dataservice.data.dao.accounts.SavingsAccount;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
public class NewSavingsAccountRequest extends NewAccountRequest {

    BigDecimal minimumBalance;
    BigDecimal interestRate;
    
}
