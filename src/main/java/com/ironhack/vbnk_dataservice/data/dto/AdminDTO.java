package com.ironhack.vbnk_dataservice.data.dto;

import com.ironhack.vbnk_dataservice.data.dao.VBAdmin;

public class AdminDTO extends VBUserDTO{

    public static AdminDTO fromEntity(VBAdmin entity) {
        var retVal = new AdminDTO();
        retVal.setName(entity.getName())
                .setId(entity.getId());
        return retVal;
    }
}
