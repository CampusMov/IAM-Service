package com.campusmov.platform.iamservice.iam.domain.model.commands;

public record SignInCommand(String email) {
    public SignInCommand {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }

    }
}
