package com.ironhack.vbnk_dataservice.controllers.impl;

import com.ironhack.vbnk_dataservice.controllers.VBNKController;
import com.ironhack.vbnk_dataservice.data.AccountStatus;
import com.ironhack.vbnk_dataservice.data.dto.accounts.AccountDTO;
import com.ironhack.vbnk_dataservice.data.http.request.NotificationRequest;
import com.ironhack.vbnk_dataservice.data.http.request.TransferRequest;
import com.ironhack.vbnk_dataservice.data.http.response.DataResponse;
import com.ironhack.vbnk_dataservice.data.http.response.TransferResponse;
import com.ironhack.vbnk_dataservice.repositories.users.AccountHolderRepository;
import com.ironhack.vbnk_dataservice.services.VBAccountService;
import com.ironhack.vbnk_dataservice.utils.VBError;
import org.apache.http.client.HttpResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/v1/data/client")
public class VBNKControllerOpen implements VBNKController {
    @Autowired
    AccountHolderRepository userRepository;
    @Autowired
    VBAccountService accountService;

    @Override
    @PostMapping("/tf/complete")
    public ResponseEntity<TransferResponse> transferFunds(TransferRequest request) {
        var response = new TransferResponse().setRequest(request);
        List<VBError> errors = response.getErrors();
        if (userRepository.existsById(request.getSourceOwnerId())) {
            AccountDTO sourceAccount = null;
            try {
                sourceAccount = accountService.getAccount(request.getSourceAccountRef());
            } catch (HttpResponseException e) {
                errors.add(VBError.ACCOUNT_NOT_FOUND);
            }
            if (sourceAccount != null && sourceAccount.getStatus().equals(AccountStatus.ACTIVE)) {
                BigDecimal prevSrcAmount = sourceAccount.getAmount();
                if (prevSrcAmount.compareTo(request.getAmount()) >= 0) {
                    try {
                        if (accountService.exist(request.getDestinationAccountRef())) {
                            AccountDTO destAccount = null;
                            destAccount = accountService.getAccount(request.getDestinationAccountRef());
                            if (destAccount != null && destAccount.getStatus().equals(AccountStatus.ACTIVE)) {
                                //--------SUCCESS! -------//
                                var prevDestAmount = destAccount.getAmount();
                                response.setDestPreviousBalance(prevDestAmount);
                                accountService.update(destAccount.setAmount(prevDestAmount
                                        .add(request.getAmount())), destAccount.getId());
                            }
                        } else {
                            // TODO: 13/09/2022  THIRD PARTY BLIND TRANSFER
                        }
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

    @Override
    @PostMapping("/tf/destlvl")
    public ResponseEntity<TransferResponse> transferFunds_destinationLevel(TransferRequest request) {
        var response = new TransferResponse().setRequest(request);
        List<VBError> errors = response.getErrors();
        if (accountService.exist(request.getDestinationAccountRef())) {
            AccountDTO destAccount = null;
            try {
                destAccount = accountService.getAccount(request.getDestinationAccountRef());
                if (destAccount != null && destAccount.getStatus().equals(AccountStatus.ACTIVE)) {
                    //--------SUCCESS! -------//
                    var prevDestAmount = destAccount.getAmount();
                    accountService.update(destAccount.setAmount(prevDestAmount
                            .add(request.getAmount())), destAccount.getId());
                }
            } catch (HttpResponseException e) {
                errors.add(VBError.FATAL_ERROR);
            }
        } else {
            // TODO: 13/09/2022  THIRD PARTY BLIND TRANSFER
        }
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<DataResponse> sendNotificationToUser(NotificationRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<DataResponse> sendNotificationToAdmin(NotificationRequest request) {
        return null;
    }
}
