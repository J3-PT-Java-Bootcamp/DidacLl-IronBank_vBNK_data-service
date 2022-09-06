package com.ironhack.vbnk_dataservice.data.dao;

import com.ironhack.vbnk_dataservice.data.Address;
import com.ironhack.vbnk_dataservice.data.dto.AccountHolderDTO;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@Entity
public class AccountHolder extends VBUser{
    @NotNull
    LocalDate dateOfBirth;
    @NotNull @Embedded
    Address primaryAddress;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "mail_street")),
            @AttributeOverride(name = "city", column = @Column(name = "mail_city")),
            @AttributeOverride(name = "country", column = @Column(name = "mail_country")),
            @AttributeOverride(name = "additionalInfo", column = @Column(name = "mail_additional_info")),
            @AttributeOverride(name = "streetNumber", column = @Column(name = "mail_street_number")),
            @AttributeOverride(name = "zipCode", column = @Column(name = "mail_zip_code")),
    })
    Address mailingAddress;

    public static AccountHolder fromDTO(AccountHolderDTO dto){
        var retVal= new AccountHolder().setDateOfBirth(dto.getDateOfBirth())
                .setPrimaryAddress(dto.getPrimaryAddress())
                .setMailingAddress(dto.getMailingAddress());
        retVal.setId(dto.getId()).setName(dto.getName());
        return retVal;
    }
}
