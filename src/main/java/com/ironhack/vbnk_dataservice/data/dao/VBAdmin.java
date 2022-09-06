package com.ironhack.vbnk_dataservice.data.dao;

import com.ironhack.vbnk_dataservice.data.dto.AdminDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Setter @Getter
@NoArgsConstructor
@Entity
public class VBAdmin extends VBUser{
    @OneToMany
    List<Notification> pendingNotifications;

    public static VBAdmin fromDTO(AdminDTO dto) {
        var retVal = new VBAdmin();
        retVal.setId(dto.getId()).setName(dto.getName());
        return retVal;
    }
}
