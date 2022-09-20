package com.ironhack.vbnk_dataservice.controllers.impl;

import com.ironhack.vbnk_dataservice.controllers.NotificationController;
import com.ironhack.vbnk_dataservice.data.dto.NotificationDTO;
import com.ironhack.vbnk_dataservice.data.http.request.NotificationRequest;
import com.ironhack.vbnk_dataservice.services.NotificationService;
import org.apache.http.HttpException;
import org.apache.http.client.HttpResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ironhack.vbnk_dataservice.utils.VBNKConfig.getUserIdFromAuth;

@RestController
@RequestMapping(path = "/v1/data")
public class NotificationControllerWeb implements NotificationController {
    @Autowired
    NotificationService service;

    @Override
    @GetMapping("/main/notifications/all")
    public ResponseEntity<List<NotificationDTO>> getAll(Authentication auth) throws HttpException {
        return ResponseEntity.ok(service.getAllPending(getUserIdFromAuth(auth)));
    }

    @Override
    @GetMapping("/dev/notifications/incoming")
    public ResponseEntity<List<NotificationDTO>> getIncoming(@RequestParam String userId) {
        return ResponseEntity.ok(service.getIncomingNotifications(userId));
    }

    @Override
    @GetMapping("/dev/notifications/fraud")
    public ResponseEntity<List<NotificationDTO>> getFraud(@RequestParam String userId) {
        return ResponseEntity.ok(service.getFraudNotifications(userId));
    }

    @Override
    @GetMapping("/dev/notifications/payment")
    public ResponseEntity<List<NotificationDTO>> getPaymentConfirm(@RequestParam String userId) {
        return ResponseEntity.ok(service.getPaymentNotifications(userId));
    }

    @Override
    @PostMapping("/dev/notifications")
    public void createNotification(@RequestBody NotificationRequest request) throws HttpResponseException {
        service.create(request);
    }

    @Override
    @DeleteMapping("/dev/notifications")
    public void delete(@RequestParam Long id) throws HttpResponseException {
        service.delete(id);
    }
}
