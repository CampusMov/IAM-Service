package com.campusmov.platform.iamservice.iam.infrastructure.model;

import com.campusmov.platform.iamservice.iam.interfaces.rest.resources.VerifyUserResource;
import lombok.Getter;
import lombok.Setter;

public class VerifyUser {

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String verificationCode;

    public VerifyUser() {
    }
    public VerifyUser(VerifyUserResource resource) {
        this.email = resource.email();
        this.verificationCode = resource.code();
    }
}
