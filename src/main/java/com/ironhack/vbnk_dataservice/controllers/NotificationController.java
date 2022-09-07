package com.ironhack.vbnk_dataservice.controllers;

import com.ironhack.vbnk_dataservice.data.dto.*;
import org.apache.http.HttpException;
import org.apache.http.client.HttpResponseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface NotificationController {

    //------------------------------------------------------------------------------GET END POINTS
    ResponseEntity<List<NotificationDTO>> getAll(String userId) throws HttpException;

    ResponseEntity<List<NotificationDTO>> getIncoming(String userId);

    ResponseEntity<List<NotificationDTO>> getFraud(String userId);

    ResponseEntity<List<NotificationDTO>> getPaymentConfirm(String id);

    //------------------------------------------------------------------------------CREATE END POINTS
    void createNotification(@RequestBody CreateNotificationDTO dto) throws HttpResponseException;

    //------------------------------------------------------------------------------DELETE END POINTS

    void delete(String id) throws HttpResponseException;


}
