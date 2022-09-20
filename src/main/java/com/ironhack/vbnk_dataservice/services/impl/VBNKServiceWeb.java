package com.ironhack.vbnk_dataservice.services.impl;

import com.ironhack.vbnk_dataservice.data.AccountState;
import com.ironhack.vbnk_dataservice.data.dto.accounts.AccountDTO;
import com.ironhack.vbnk_dataservice.data.dto.users.AdminDTO;
import com.ironhack.vbnk_dataservice.data.dto.users.ThirdPartyDTO;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.ironhack.vbnk_dataservice.data.NotificationType.INCOMING;
import static org.springframework.http.HttpStatus.FORBIDDEN;

@Service
public class VBNKServiceWeb implements VBNKService {
    @Autowired
    private VBUserService userService;
    @Autowired
    private VBAccountService accountService;
    @Autowired
    private NotificationService notificationService;

    @Override
    public ResponseEntity<TransferResponse> transferFunds(TransferRequest request) throws HttpResponseException {
        var response = new TransferResponse().setRequest(request);
        List<VBError> errors = response.getErrors()==null? new ArrayList<>():response.getErrors();
        if (userService.existsById(request.getOrderingUserId())) {
            var sender= userService.getUnknown(request.getOrderingUserId());
            AccountDTO sourceAccount = null;
            try {
                sourceAccount = accountService.getAccount(request.getFromAccount());
            } catch (HttpResponseException e) {
                errors.add(VBError.ACCOUNT_NOT_FOUND);
            }
            if (sourceAccount != null && sourceAccount.getState().equals(AccountState.ACTIVE)) {
                if(!accountService.isOwnedBy(sourceAccount,sender.getId())&&!(sender instanceof AdminDTO))
                    throw new HttpResponseException(FORBIDDEN.value(), FORBIDDEN.getReasonPhrase());
                BigDecimal prevSrcAmount = sourceAccount.getAmount();
                if (prevSrcAmount.compareTo(request.getAmount()) >= 0) {
                    try {
                        response = transferFunds_receiveTransfer(response);
                        prevSrcAmount= accountService.update(sourceAccount.setAmount(prevSrcAmount
                                .subtract(request.getAmount())), sourceAccount.getId()).getAmount();
                        response.setSource(true).setSrcBalance(prevSrcAmount);

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
        if (accountService.exist(request.getFromAccount())) {
            AccountDTO destAccount = null;
            destAccount = accountService.getAccount(request.getToAccount());
            if (destAccount != null && destAccount.getState().equals(AccountState.ACTIVE)) {
                //--------SUCCESS! -------//
                var prevDestAmount = destAccount.getAmount();
                response.setDstBalance(prevDestAmount);
                prevDestAmount= accountService.update(destAccount.setAmount(prevDestAmount
                        .add(request.getAmount())), destAccount.getId()).getAmount();
                response.setDestination(true).setDstBalance(prevDestAmount);
                sendNotification(new NotificationRequest("New income",
                        "You have a new income in your account.",
                        INCOMING,
                        destAccount.getPrimaryOwner().getId()));
            }
        } else {
            ThirdPartyDTO tpUser = null;
            try {
                tpUser = userService.getThirdPartyFromAccountNumber(request.getFromAccount());
            }catch (Throwable ignored){
            }
            if(tpUser==null)throw new HttpResponseException(HttpStatus.UNAUTHORIZED.value(), "Unknown third party service, first must be registered");
            AccountDTO destAccount = null;
            destAccount = accountService.getAccount(request.getToAccount());
            if (destAccount != null && destAccount.getState().equals(AccountState.ACTIVE)) {
                //--------SUCCESS! -------//
                var prevDestAmount = destAccount.getAmount();
                response.setDstBalance(prevDestAmount);
                prevDestAmount= accountService.update(destAccount.setAmount(prevDestAmount
                        .add(request.getAmount())), destAccount.getId()).getAmount();
                response.setDestination(true).setDstBalance(prevDestAmount);
                sendNotification(new NotificationRequest("New income",
                        "You have a new income in your account.",
                        INCOMING,
                        destAccount.getPrimaryOwner().getId()));
            }
        }
        return response;
    }

    @Override
    public ResponseEntity<TransferResponse> receiveTransfer(TransferRequest request) throws HttpResponseException {
        var response = new TransferResponse().setRequest(request);
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
