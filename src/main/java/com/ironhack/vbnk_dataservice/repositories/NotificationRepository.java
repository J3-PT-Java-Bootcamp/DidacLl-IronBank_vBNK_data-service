package com.ironhack.vbnk_dataservice.repositories;

import com.ironhack.vbnk_dataservice.data.NotificationState;
import com.ironhack.vbnk_dataservice.data.NotificationType;
import com.ironhack.vbnk_dataservice.data.dao.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long>, JpaSpecificationExecutor<Notification> {
    List<Notification> getAllByOwnerId(String id);

    List<Notification> getAllByOwnerIdAndState(String id, NotificationState state);

    List<Notification> getAllByOwnerIdAndType(String id, NotificationType type);

    List<Notification> getAllByOwnerIdAndTypeAndState(String id, NotificationType type, NotificationState state);

}