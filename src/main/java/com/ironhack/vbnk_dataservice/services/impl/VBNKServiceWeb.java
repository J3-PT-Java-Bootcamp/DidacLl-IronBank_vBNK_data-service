package com.ironhack.vbnk_dataservice.services.impl;

import com.ironhack.vbnk_dataservice.data.AccountState;
import com.ironhack.vbnk_dataservice.data.dto.accounts.AccountDTO;
import com.ironhack.vbnk_dataservice.data.http.request.NotificationRequest;
import com.ironhack.vbnk_dataservice.data.http.request.TransferRequest;
import com.ironhack.vbnk_dataservice.data.http.response.DataResponse;
import com.ironhack.vbnk_dataservice.data.http.response.TransferResponse;
import com.ironhack.vbnk_dataservice.services.NotificationService;
import com.ironhack.vbnk_dataservice.services.VBAccountService;
import com.ironhack.vbnk_dataservice.services.VBNKService;
import com.ironhack.vbnk_dataservice.services.VBUserService;
import com.ironhack.vbnk_dataservice.utils.VBError;
import org.apache.http.client.HttpResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class VBNKServiceWeb implements VBNKService {
    @Autowired
    private VBUserService userService;
    @Autowired
    private VBAccountService accountService;
    @Autowired
    private NotificationService notificationService;

    @Override
    public ResponseEntity<TransferResponse> transferFunds(TransferRequest request) {
        var response = new TransferResponse().setRequest(request);
        List<VBError> errors = response.getErrors();
        if (userService.existsById(request.getSourceOwnerId())) {
            AccountDTO sourceAccount = null;
            try {
                sourceAccount = accountService.getAccount(request.getSourceAccountRef());
            } catch (HttpResponseException e) {
                errors.add(VBError.ACCOUNT_NOT_FOUND);
            }
            if (sourceAccount != null && sourceAccount.getState().equals(AccountState.ACTIVE)) {
                BigDecimal prevSrcAmount = sourceAccount.getAmount();
                if (prevSrcAmount.compareTo(request.getAmount()) >= 0) {
                    try {
                        response = transferFunds_receiveTransfer(response);
                        accountService.update(sourceAccount.setAmount(prevSrcAmount
                                .subtract(request.getAmount())), sourceAccount.getId());
                        response.setSourceLevelOk(true).setSrcPreviousBalance(prevSrcAmount);

                    } catch (HttpResponseException e) {
                        errors.add(VBError.FATAL_ERROR);
                    }
                } else errors.add(VBError.NOT_ENOUGH_FOUNDS);
            } else {
                errors.add(VBError.UNAVAILABLE_ACCOUNT);
            }

        } else {
            errors.add(VBError.USER_NOT_FOUND);
        }
        response.setErrors(errors);
        return ResponseEntity.ok(response);
    }

    private TransferResponse transferFunds_receiveTransfer(TransferResponse response) throws HttpResponseException {
        var request = response.getRequest();
        if (accountService.exist(request.getDestinationAccountRef())) {
            AccountDTO destAccount = null;
            destAccount = accountService.getAccount(request.getDestinationAccountRef());
            if (destAccount != null && destAccount.getState().equals(AccountState.ACTIVE)) {
                //--------SUCCESS! -------//
                var prevDestAmount = destAccount.getAmount();
                response.setDestPreviousBalance(prevDestAmount);
                accountService.update(destAccount.setAmount(prevDestAmount
                        .add(request.getAmount())), destAccount.getId());
                response.setDestLevelOk(true);
            }
        } else {
            // TODO: 13/09/2022  THIRD PARTY BLIND TRANSFER
        }
        return response;
    }

    @Override
    public ResponseEntity<TransferResponse> receiveTransfer(TransferRequest request) throws HttpResponseException {
        var response = new TransferResponse().setRequest(request);
        List<VBError> errors = response.getErrors();

        return ResponseEntity.ok(transferFunds_receiveTransfer(response));
    }

    @Override
    public ResponseEntity<TransferResponse> sendBlindTransfer(TransferRequest request) throws HttpResponseException {
        // TODO: 17/09/2022
        return null;
    }

    @Override
    public ResponseEntity<DataResponse> sendNotification(NotificationRequest request) {
        var response = new DataResponse();
        var errors = new ArrayList<VBError>();
        try {
            response.setOk(notificationService.create(request) != null);
        } catch (HttpResponseException e) {
            errors.add(VBError.USER_NOT_FOUND);
        }
        response.setError(errors);
        return ResponseEntity.ok(response);
    }

}
