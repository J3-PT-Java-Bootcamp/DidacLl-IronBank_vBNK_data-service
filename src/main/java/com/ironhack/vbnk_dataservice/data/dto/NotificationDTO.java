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
    private Long id;
    private String title;
    private  String message;
    private NotificationType type;
    private  NotificationState state;
    private VBUser owner;


    public static NotificationDTO fromEntity(Notification entity) {
        return new NotificationDTO().setId(entity.getId())
                .setType(entity.getType())
                .setOwner(entity.getOwner())
                .setState(entity.getState())
                .setMessage(entity.getMessage())
                .setTitle(entity.getTitle());
    }
}
