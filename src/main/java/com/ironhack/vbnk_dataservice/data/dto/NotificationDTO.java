package com.ironhack.vbnk_dataservice.data.dto;

import com.ironhack.vbnk_dataservice.data.NotificationState;
import com.ironhack.vbnk_dataservice.data.NotificationType;
import com.ironhack.vbnk_dataservice.data.dao.Notification;
import com.ironhack.vbnk_dataservice.data.dao.users.VBUser;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationDTO {
    Long id;
    String title;
    String message;
    NotificationType type;
    NotificationState state;
    VBUser owner;


    public static NotificationDTO fromEntity(Notification entity) {
        return new NotificationDTO().setId(entity.getId())
                .setType(entity.getType())
                .setOwner(entity.getOwner())
                .setState(entity.getState())
                .setMessage(entity.getMessage())
                .setTitle(entity.getTitle());
    }
}
