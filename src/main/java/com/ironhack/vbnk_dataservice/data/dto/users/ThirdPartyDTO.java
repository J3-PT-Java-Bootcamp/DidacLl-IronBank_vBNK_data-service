package com.ironhack.vbnk_dataservice.data.dto.users;

import com.ironhack.vbnk_dataservice.data.dao.users.ThirdParty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ThirdPartyDTO extends VBUserDTO {

    String hashKey;
    boolean trusted;

    public static ThirdPartyDTO fromEntity(ThirdParty entity) {
        return newThirdPartyDTO(entity.getUsername(), entity.getId()).setHashKey(entity.getHashKey()).setTrusted(entity.isTrusted());
    }

    public static ThirdPartyDTO newThirdPartyDTO(String name, String id) {
        var user = new ThirdPartyDTO();
        user.setId(id).setUsername(name);
        return user;
    }
}
