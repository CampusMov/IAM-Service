package com.campusmov.platform.iamservice.iam.infrastructure.model;

import lombok.Getter;
import lombok.Setter;

public class Email {

    @Getter
    @Setter
    private String addressee;

    @Getter
    @Setter
    private String subject;

    @Getter
    @Setter
    private String verificationCode;

    public Email(String addressee, String subject, String verificationCode) {
        this.addressee = addressee;
        this.subject = subject;
        this.verificationCode = verificationCode;
    }
}
