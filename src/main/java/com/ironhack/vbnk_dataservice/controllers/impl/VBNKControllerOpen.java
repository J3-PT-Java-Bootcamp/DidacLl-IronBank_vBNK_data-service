package com.ironhack.vbnk_dataservice.controllers.impl;

import com.ironhack.vbnk_dataservice.controllers.VBNKController;
import com.ironhack.vbnk_dataservice.data.http.request.NotificationRequest;
import com.ironhack.vbnk_dataservice.data.http.request.ThirdPartyTransferRequest;
import com.ironhack.vbnk_dataservice.data.http.request.TransferRequest;
import com.ironhack.vbnk_dataservice.data.http.response.DataResponse;
import com.ironhack.vbnk_dataservice.data.http.response.TransferResponse;
import com.ironhack.vbnk_dataservice.services.VBNKService;
import io.swagger.v3.oas.annotations.Hidden;
import org.apache.http.client.HttpResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.naming.ServiceUnavailableException;

@RestController
@RequestMapping("/v1/data")
public class VBNKControllerOpen implements VBNKController {
    @Autowired
    VBNKService service;

    @Hidden
    @Override
    @PostMapping("/main/tf/send")
    public ResponseEntity<TransferResponse> transferFunds(Authentication auth, @RequestBody TransferRequest request) throws HttpResponseException {
        return service.transferFunds(request);
    }

    @Hidden
    @Override
    @PostMapping("/client/tf/receive")
    public ResponseEntity<TransferResponse> transferFunds_destinationLevel(@RequestBody ThirdPartyTransferRequest request) throws HttpResponseException {
        return service.receiveTransfer(request);
    }

    @Hidden
    @Override
    @PostMapping("/client/notif")
    public ResponseEntity<DataResponse> sendNotification(@RequestBody NotificationRequest request) {
        return service.sendNotification(request);
    }
    @Hidden @Override
    @GetMapping("/client/update")
    public void startBankUpdate() throws HttpResponseException, ServiceUnavailableException {
        service.bankUpdateUsers();
    }


}
