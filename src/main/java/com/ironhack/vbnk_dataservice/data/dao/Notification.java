package com.ironhack.vbnk_dataservice.data.dao;

import com.ironhack.vbnk_dataservice.data.NotificationState;
import com.ironhack.vbnk_dataservice.data.NotificationType;
import com.ironhack.vbnk_dataservice.data.dao.users.VBUser;
import com.ironhack.vbnk_dataservice.data.dto.NotificationDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Notification {
    @Id
    @GeneratedValue
    Long id;
    String title;
    String message;
    @Enumerated(EnumType.STRING)
    NotificationType type;
    @Enumerated(EnumType.STRING)
    NotificationState state;
    @ManyToOne(fetch = FetchType.EAGER)
    VBUser owner;

    public final Notification fromDTO(NotificationDTO entity){
        return new Notification().setId(entity.getId())
                .setType(entity.getType())
                .setOwner(entity.getOwner())
                .setState(entity.getState())
                .setMessage(entity.getMessage())
                .setTitle(entity.getTitle());
    }
}
