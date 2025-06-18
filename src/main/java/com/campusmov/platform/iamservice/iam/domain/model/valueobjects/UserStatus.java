package com.campusmov.platform.iamservice.iam.domain.model.valueobjects;

public enum UserStatus {
    NOT_VERIFIED,
    VERIFIED,
    ACTIVE,
    BLOCKED,
    DELETED;

    public String statusName() {
        return this.name();
    }
}
