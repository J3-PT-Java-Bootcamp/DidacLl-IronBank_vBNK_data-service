package com.ironhack.vbnk_dataservice.data.dao;

import com.ironhack.vbnk_dataservice.data.Money;
import com.ironhack.vbnk_dataservice.utils.MoneyConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Convert;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class SavingsAccount extends VBAccount {
    BigDecimal interestRate, penaltyFee;
    @Convert(converter = MoneyConverter.class)
    Money minimumBalance;
}
