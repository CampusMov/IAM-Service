package com.campusmov.platform.iamservice.iam.infrastructure.services;

import com.campusmov.platform.iamservice.iam.infrastructure.model.Email;

public interface EmailService {
    void sendEmail(Email email);
}
