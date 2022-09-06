package com.ironhack.vbnk_dataservice.data.dao;

import com.ironhack.vbnk_dataservice.data.dto.ThirdPartyDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Setter @Getter
@NoArgsConstructor
@Entity
public class ThirdParty extends VBUser{
    String hashKey;
    boolean trusted;



    public static ThirdParty fromDTO(ThirdPartyDTO dto) {
        var retVal = new ThirdParty().setHashKey(dto.getHashKey()).setTrusted(dto.isTrusted());
        retVal.setId(dto.getId()).setName(dto.getName());
        return retVal;
    }
}
