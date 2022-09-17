package com.ironhack.vbnk_dataservice.data.http.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public abstract class NewAccountRequest {
//    String id;
    String accountNumber;
    BigDecimal initialAmount;
    String currency;
    String secretKey;
    String primaryOwner;
    String secondaryOwner;
    String administratedBy;


}
