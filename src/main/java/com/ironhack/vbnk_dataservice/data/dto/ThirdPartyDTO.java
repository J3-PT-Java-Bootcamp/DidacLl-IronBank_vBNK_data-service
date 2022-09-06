package com.ironhack.vbnk_dataservice.data.dto;

import com.ironhack.vbnk_dataservice.data.dao.ThirdParty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class ThirdPartyDTO extends VBUserDTO{

    String hashKey;
    boolean trusted;

    public static ThirdPartyDTO fromEntity(ThirdParty entity) {
        var retVal = new ThirdPartyDTO().setHashKey(entity.getHashKey()).setTrusted(entity.isTrusted());
        retVal.setName(entity.getName())
                .setId(entity.getId());
        return retVal;
    }
}
