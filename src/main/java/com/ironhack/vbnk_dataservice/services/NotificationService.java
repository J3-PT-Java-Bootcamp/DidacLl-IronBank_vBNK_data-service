package com.ironhack.vbnk_dataservice.services;

import com.ironhack.vbnk_dataservice.data.dao.Notification;
import com.ironhack.vbnk_dataservice.data.dto.CreateNotificationDTO;
import com.ironhack.vbnk_dataservice.data.dto.NotificationDTO;
import com.ironhack.vbnk_dataservice.repositories.NotificationRepository;
import org.apache.http.client.HttpResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface NotificationService {
    List<NotificationDTO> getAllPending(String userId);

    List<NotificationDTO> getIncomingNotifications(String userId);

    List<NotificationDTO> getFraudNotifications(String userId);

    List<NotificationDTO> getPaymentNotifications(String userId);

    NotificationDTO create(CreateNotificationDTO dto) throws HttpResponseException;

    void delete(Long id);
}
