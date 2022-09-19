package com.ironhack.vbnk_dataservice.data;

public enum NotificationType {
    INCOMING(2),
    FRAUD(0),
    PAYMENT_CONFIRM(2),
    BANK_CHARGES_INTERESTS(0);
    private final int expirationDays;

    NotificationType(int expirationDays) {
        this.expirationDays = expirationDays;
    }
}
