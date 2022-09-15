package com.ironhack.vbnk_dataservice.data.dto.users;

import com.ironhack.vbnk_dataservice.data.Address;
import com.ironhack.vbnk_dataservice.data.dao.users.AccountHolder;
import com.ironhack.vbnk_dataservice.data.http.request.NewAccountHolderRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class AccountHolderDTO extends VBUserDTO {
    LocalDate dateOfBirth;
    Address primaryAddress;
    Address mailingAddress;

    public AccountHolderDTO(String id) {
        super();
        this.setId(id);
    }

    public static AccountHolderDTO fromEntity(AccountHolder entity) {
        return newAccountHolderDTO(entity.getName(), entity.getId()).setDateOfBirth(entity.getDateOfBirth())
                .setMailingAddress(entity.getMailingAddress())
                .setPrimaryAddress(entity.getPrimaryAddress());
    }

    public static AccountHolderDTO newAccountHolderDTO(String name, String id) {
        var user = new AccountHolderDTO();
        user.setId(id).setName(name);
        return user;
    }

    @Override
    public AccountHolderDTO setName(String name) {
        super.setName(name);
        return this;
    }

    public static AccountHolderDTO fromRequest(NewAccountHolderRequest request){
        return newAccountHolderDTO(request.getName(), request.getId()).setDateOfBirth(request.getDateOfBirth())
                .setMailingAddress(new Address(request.getMailStreet(),
                        request.getMailCity(),
                        request.getMailCountry(),
                        request.getMailAdditionalInfo(),
                        request.getMailStreetNumber(),
                        request.getMailZipCode()))
                .setPrimaryAddress(new Address(request.getMainStreet(),
                        request.getMainCity(),
                        request.getMainCountry(),
                        request.getMainAdditionalInfo(),
                        request.getMainStreetNumber(),
                        request.getMainZipCode()));
    }
}
