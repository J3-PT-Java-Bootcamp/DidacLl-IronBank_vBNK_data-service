package com.ironhack.vbnk_dataservice.data;

public enum NotificationType {
    INCOMING(2),
    FRAUD(0),
    PAYMENT_CONFIRM(2);
    private final int expirationDays;

    NotificationType(int expirationDays) {
        this.expirationDays = expirationDays;
    }
}
