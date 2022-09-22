package com.ironhack.vbnk_dataservice.services.impl;

import com.ironhack.vbnk_dataservice.data.NotificationState;
import com.ironhack.vbnk_dataservice.data.NotificationType;
import com.ironhack.vbnk_dataservice.data.dao.Notification;
import com.ironhack.vbnk_dataservice.data.dao.users.VBUser;
import com.ironhack.vbnk_dataservice.data.dto.NotificationDTO;
import com.ironhack.vbnk_dataservice.data.http.request.NotificationRequest;
import com.ironhack.vbnk_dataservice.repositories.NotificationRepository;
import com.ironhack.vbnk_dataservice.services.NotificationService;
import com.ironhack.vbnk_dataservice.services.VBAccountService;
import com.ironhack.vbnk_dataservice.services.VBUserService;
import org.apache.http.client.HttpResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository repository;
    private final VBUserService userService;
    private final VBAccountService accountService;

    public NotificationServiceImpl(NotificationRepository repository, VBUserService userService, VBAccountService accountService) {
        this.repository = repository;
        this.userService = userService;
        this.accountService = accountService;
    }

    @Override
    public List<NotificationDTO> getAllPending(String userId) {
        return repository.findAllByOwnerIdAndState(userId, NotificationState.PENDING)
                .stream().map(NotificationDTO::fromEntity)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public List<NotificationDTO> getIncomingNotifications(String userId) {
        return repository.findAllByOwnerIdAndTypeAndState(userId,
                        NotificationType.INCOMING,
                        NotificationState.PENDING)
                .stream().map(NotificationDTO::fromEntity)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public List<NotificationDTO> getFraudNotifications(String userId) {
        List<Notification> all = repository.findAllByOwnerIdAndTypeAndState(userId,
                NotificationType.FRAUD,
                NotificationState.PENDING);
        return all.stream().map(NotificationDTO::fromEntity)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public List<NotificationDTO> getPaymentNotifications(String userId) {
        return repository.findAllByOwnerIdAndTypeAndState(userId,
                        NotificationType.PAYMENT_CONFIRM,
                        NotificationState.PENDING)
                .stream().map(NotificationDTO::fromEntity)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public NotificationDTO create(NotificationRequest request) throws HttpResponseException {
        if(!userService.existsById(request.getOwnerId()))
            request.setOwnerId(accountService.getAccount(request.getOwnerId()).getPrimaryOwner().getId());
        return NotificationDTO.fromEntity(repository.save(
                new Notification().setType(request.getType())
                        .setMessage(request.getMessage()).setTitle(request.getTitle())
                        .setState(NotificationState.PENDING)
                        .setOwner(VBUser.fromUnknownDTO(userService.getUnknown(request.getOwnerId())))));
    }
    @Override
    public void bankUpdateNotification(String userId){
        var notifications= getAllPending(userId);
        for (NotificationDTO note: notifications){
            var exp= note.getType().getExpirationDays();
            if(exp==0)break;
            if(note.getCreationDate().plus(exp, ChronoUnit.DAYS).isBefore(Instant.now()))
                delete(note.getId());
        }

    }
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
