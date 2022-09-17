package com.ironhack.vbnk_dataservice.data.http.request;

import com.ironhack.vbnk_dataservice.data.dao.accounts.CreditAccount;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
public class NewCreditAccountRequest extends NewAccountRequest {

    BigDecimal creditLimit;
    BigDecimal interestRate;


}
