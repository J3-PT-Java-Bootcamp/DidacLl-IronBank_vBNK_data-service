package com.ironhack.vbnk_dataservice.data.http.request;

import com.ironhack.vbnk_dataservice.data.dao.accounts.SavingsAccount;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
public class NewSavingsAccountRequest extends NewAccountRequest {
    @DecimalMax(value = "1000",message = "Minimum balance must be between 100 and 1000 ")
    @DecimalMin(value = "100",message = "Minimum balance must be between 100 and 1000 ")
    private  BigDecimal minimumBalance;
    @DecimalMax(value = "0.5",message = "Interest Rate must be less than 0.5")
    private  BigDecimal interestRate;
    
}
