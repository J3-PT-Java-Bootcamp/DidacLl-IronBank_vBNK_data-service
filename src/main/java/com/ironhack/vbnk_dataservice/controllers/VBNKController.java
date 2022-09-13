package com.ironhack.vbnk_dataservice.controllers;

import com.ironhack.vbnk_dataservice.data.http.request.NotificationRequest;
import com.ironhack.vbnk_dataservice.data.http.request.TransferRequest;
import com.ironhack.vbnk_dataservice.data.http.response.DataResponse;
import com.ironhack.vbnk_dataservice.data.http.response.TransferResponse;
import org.springframework.http.ResponseEntity;

public interface VBNKController {

    ResponseEntity<TransferResponse> transferFunds(TransferRequest request);

    ResponseEntity<TransferResponse> transferFunds_destinationLevel(TransferRequest request);

    ResponseEntity<DataResponse> sendNotificationToUser(NotificationRequest request);

    ResponseEntity<DataResponse> sendNotificationToAdmin(NotificationRequest request);


}
