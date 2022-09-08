package com.ironhack.vbnk_dataservice.data.dao.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ironhack.vbnk_dataservice.data.dao.Notification;
import com.ironhack.vbnk_dataservice.data.dto.users.AdminDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class VBAdmin extends VBUser {
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    @JsonIgnore
    List<Notification> pendingNotifications;

    public static VBAdmin fromDTO(AdminDTO dto) {
        return newVBAdmin(dto.getName(), dto.getId());
    }

    public static VBAdmin newVBAdmin(String name, String id) {
        var user = new VBAdmin();
        user.setId(id).setName(name);
        return user;
    }
}
