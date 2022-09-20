package com.ironhack.vbnk_dataservice.data.http.views;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Currency;
@Getter
@NoArgsConstructor
public class StatementView {
    BigDecimal amount;
    BigDecimal balance;
    Currency currency;
    Instant date;
    String type;
    String senderDisplayName;
    String concept;
}
