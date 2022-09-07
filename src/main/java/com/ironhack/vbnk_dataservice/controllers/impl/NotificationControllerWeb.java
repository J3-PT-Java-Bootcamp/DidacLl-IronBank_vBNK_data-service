package com.ironhack.vbnk_dataservice.controllers.impl;

import com.ironhack.vbnk_dataservice.controllers.NotificationController;
import com.ironhack.vbnk_dataservice.data.dto.CreateNotificationDTO;
import com.ironhack.vbnk_dataservice.data.dto.NotificationDTO;
import com.ironhack.vbnk_dataservice.services.NotificationService;
import org.apache.http.HttpException;
import org.apache.http.client.HttpResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "v1/users/notifications")
public class NotificationControllerWeb implements NotificationController {
    @Autowired
    NotificationService service;
    @Override
    @GetMapping("/all")
    public ResponseEntity<List<NotificationDTO>> getAll(@RequestParam String userId) throws HttpException {
        return ResponseEntity.ok(service.getAllPending(userId));
    }

    @Override
    @GetMapping("/incoming")
    public ResponseEntity<List<NotificationDTO>> getIncoming(@RequestParam String userId) {
        return ResponseEntity.ok(service.getIncomingNotifications(userId));
    }

    @Override
    @GetMapping("/fraud")
    public ResponseEntity<List<NotificationDTO>> getFraud(@RequestParam String userId) {
        return ResponseEntity.ok(service.getFraudNotifications(userId));
    }

    @Override
    @GetMapping("/payment")
    public ResponseEntity<List<NotificationDTO>> getPaymentConfirm(@RequestParam String userId) {
        return ResponseEntity.ok(service.getPaymentNotifications(userId));
    }

    @Override
    @PostMapping
    public void createNotification(@RequestBody CreateNotificationDTO dto) throws HttpResponseException {
        service.create(dto);
    }

    @Override
    @DeleteMapping
    public void delete(@RequestParam String id) throws HttpResponseException {
        service.delete(id);
    }
}
