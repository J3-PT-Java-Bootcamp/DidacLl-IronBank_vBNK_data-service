package com.ironhack.vbnk_dataservice.data.http.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Currency;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransferRequest {
    String sourceAccountRef, sourceOwnerId, destinationAccountRef;
    BigDecimal amount;
    Currency currency;

}
