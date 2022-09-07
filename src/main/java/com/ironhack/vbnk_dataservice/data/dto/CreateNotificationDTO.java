package com.ironhack.vbnk_dataservice.data.dto;

import com.ironhack.vbnk_dataservice.data.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
@Getter @Setter
@AllArgsConstructor
public class CreateNotificationDTO {
    String title;
    String message;
    NotificationType type;
    String ownerId;
}
