package com.campusmov.platform.iamservice.iam.domain.model.valueobjects;

public enum UserStatus {
    CREATED,
    ACTIVE,
    BLOCKED,
    DELETED;

    public String statusName() {
        return this.name();
    }



}
