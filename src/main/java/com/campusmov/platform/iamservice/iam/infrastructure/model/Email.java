package com.campusmov.platform.iamservice.iam.infrastructure.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Email {

    private String addressee;

    private String subject;

    private String verificationCode;

    public Email(String addressee, String subject, String verificationCode) {
        this.addressee = addressee;
        this.subject = subject;
        this.verificationCode = verificationCode;
    }
}
