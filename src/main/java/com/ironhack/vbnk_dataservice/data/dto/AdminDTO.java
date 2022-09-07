package com.ironhack.vbnk_dataservice.data.dto;

import com.ironhack.vbnk_dataservice.data.dao.VBAdmin;

public class AdminDTO extends VBUserDTO{

    public static AdminDTO fromEntity(VBAdmin entity) {
        return newAdminDTO(entity.getName(), entity.getId());
    }

    public static AdminDTO newAdminDTO(String name, String id){
        var user= new AdminDTO();
        user.setId(id).setName(name);
        return user;
    }
}
