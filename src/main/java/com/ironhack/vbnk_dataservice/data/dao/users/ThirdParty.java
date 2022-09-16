package com.ironhack.vbnk_dataservice.data.dao.users;

import com.ironhack.vbnk_dataservice.data.dto.users.ThirdPartyDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class ThirdParty extends VBUser {
    private String hashKey;
    private boolean trusted;


    public static ThirdParty fromDTO(ThirdPartyDTO dto) {
        return newThirdParty(dto.getUsername(), dto.getId(), dto.getFirstName(), dto.getLastName()).setTrusted(dto.isTrusted());
    }

    public static ThirdParty newThirdParty(String name, String id,String firstname,String lastname) {
        var user = new ThirdParty();
        user.setId(id).setUsername(name).setFirstName(firstname).setLastName(lastname);
        return user;
    }
}
