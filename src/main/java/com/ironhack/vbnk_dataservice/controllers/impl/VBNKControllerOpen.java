package com.ironhack.vbnk_dataservice.controllers.impl;

import com.ironhack.vbnk_dataservice.controllers.VBNKController;
import com.ironhack.vbnk_dataservice.data.http.request.NotificationRequest;
import com.ironhack.vbnk_dataservice.data.http.request.TransferRequest;
import com.ironhack.vbnk_dataservice.data.http.response.DataResponse;
import com.ironhack.vbnk_dataservice.data.http.response.TransferResponse;
import com.ironhack.vbnk_dataservice.services.VBNKService;
import org.apache.http.client.HttpResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/data/client")
public class VBNKControllerOpen implements VBNKController {
    @Autowired
    VBNKService service;

    @Override
    @PostMapping("/tf/send")
    public ResponseEntity<TransferResponse> transferFunds(TransferRequest request) {
        return service.transferFunds(request);
    }

    @Override
    @PostMapping("/tf/receive")
    public ResponseEntity<TransferResponse> transferFunds_destinationLevel(TransferRequest request) throws HttpResponseException {
        return service.receiveTransfer(request);
    }

    @Override
    @PostMapping("/notif")
    public ResponseEntity<DataResponse> sendNotification(NotificationRequest request) {
        return service.sendNotification(request);
    }

}
