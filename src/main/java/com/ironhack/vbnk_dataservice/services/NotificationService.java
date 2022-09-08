package com.ironhack.vbnk_dataservice.services;

import com.ironhack.vbnk_dataservice.data.dto.CreateNotificationDTO;
import com.ironhack.vbnk_dataservice.data.dto.NotificationDTO;
import org.apache.http.client.HttpResponseException;

import java.util.List;

public interface NotificationService {
    List<NotificationDTO> getAllPending(String userId);

    List<NotificationDTO> getIncomingNotifications(String userId);

    List<NotificationDTO> getFraudNotifications(String userId);

    List<NotificationDTO> getPaymentNotifications(String userId);

    NotificationDTO create(CreateNotificationDTO dto) throws HttpResponseException;

    void delete(Long id);
}
