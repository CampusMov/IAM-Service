package com.campusmov.platform.iamservice.iam.domain.model.commands;


public record CreateUserCommand(String email) {
    public CreateUserCommand {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
    }
}
