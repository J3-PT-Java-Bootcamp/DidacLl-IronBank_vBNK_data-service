package com.ironhack.vbnk_dataservice.data.dao;

import com.ironhack.vbnk_dataservice.data.dto.ThirdPartyDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class ThirdParty extends VBUser {
    String hashKey;
    boolean trusted;


    public static ThirdParty fromDTO(ThirdPartyDTO dto) {
        return newThirdParty(dto.getName(), dto.getId()).setHashKey(dto.getHashKey()).setTrusted(dto.isTrusted());
    }

    public static ThirdParty newThirdParty(String name, String id) {
        var user = new ThirdParty();
        user.setId(id).setName(name);
        return user;
    }
}
