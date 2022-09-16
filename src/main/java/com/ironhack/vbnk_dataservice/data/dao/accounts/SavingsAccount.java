package com.ironhack.vbnk_dataservice.data.dao.accounts;

import com.ironhack.vbnk_dataservice.data.Money;
import com.ironhack.vbnk_dataservice.data.dto.accounts.SavingsDTO;
import com.ironhack.vbnk_dataservice.utils.MoneyConverter;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;

import static com.ironhack.vbnk_dataservice.utils.VBNKConfig.*;

@Entity
//@NoArgsConstructor
@Getter
@Setter
public class SavingsAccount extends VBAccount {

    @ColumnDefault(VBNK_MAX_INTEREST_RATE)
    @DecimalMax(VBNK_MAX_INTEREST_RATE) @DecimalMin(VBNK_MIN_INTEREST_RATE)
    private BigDecimal interestRate;
    private final BigDecimal penaltyFee=VBNK_PENALTY_FEE;
    @Convert(converter = MoneyConverter.class)
    private Money minimumBalance;

    public static SavingsAccount fromDTO(SavingsDTO dto) {
        var retEntity = new SavingsAccount().setMinimumBalance(new Money(dto.getMinimumBalance(),dto.getCurrency()))
                .setInterestRate(dto.getInterestRate());
        retEntity.setId(dto.getId())
                .setBalance(new Money(dto.getAmount(),dto.getCurrency()))                .setStatus(dto.getStatus())
                .setSecretKey(dto.getSecretKey())
                .setPrimaryOwner(dto.getPrimaryOwner())
                .setSecondaryOwner(dto.getSecondaryOwner())
                .setAdministratedBy(dto.getAdministratedBy());
        return retEntity;
    }
}
