package com.ironhack.vbnk_dataservice.data.dao.accounts;

import com.ironhack.vbnk_dataservice.data.Money;
import com.ironhack.vbnk_dataservice.data.dto.accounts.CreditDTO;
import com.ironhack.vbnk_dataservice.utils.MoneyConverter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Convert;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
//@NoArgsConstructor
@Getter
@Setter
public class CreditAccount extends VBAccount {
    @Convert(converter = MoneyConverter.class)
    Money creditLimit;
    BigDecimal interestRate;

    public static CreditAccount fromDTO(CreditDTO dto) {
        var retEntity = new CreditAccount().setCreditLimit(new Money(dto.getCreditLimit(),dto.getCurrency()))
                .setInterestRate(dto.getInterestRate());
        retEntity.setId(dto.getId())

                .setBalance(new Money(dto.getAmount(),dto.getCurrency()))
                .setStatus(dto.getStatus())
                .setSecretKey(dto.getSecretKey())
                .setPrimaryOwner(dto.getPrimaryOwner())
                .setSecondaryOwner(dto.getSecondaryOwner())
                .setAdministratedBy(dto.getAdministratedBy());
        return retEntity;
    }
}
