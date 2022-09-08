package com.ironhack.vbnk_dataservice.data.dao.accounts;

import com.ironhack.vbnk_dataservice.data.Money;
import com.ironhack.vbnk_dataservice.data.dto.accounts.CheckingDTO;
import com.ironhack.vbnk_dataservice.utils.MoneyConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Convert;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class CheckingAccount extends VBAccount {
    @Convert(converter = MoneyConverter.class)
    Money minimumBalance;
    BigDecimal penaltyFee;
    BigDecimal monthlyMaintenanceFee;

    public static CheckingAccount fromDTO(CheckingDTO dto){
        var entity= new CheckingAccount().setMinimumBalance(dto.getMinimumBalance())
                .setMonthlyMaintenanceFee(dto.getMonthlyMaintenanceFee())
                .setPenaltyFee(dto.getPenaltyFee());
        entity.setId(dto.getId())
                .setBalance(dto.getBalance())
                .setStatus(dto.getStatus())
                .setSecretKey(dto.getSecretKey())
                .setPrimaryOwner(dto.getPrimaryOwner())
                .setSecondaryOwner(dto.getSecondaryOwner())
                .setAdministratedBy(dto.getAdministratedBy());
        return entity;
    }

}
