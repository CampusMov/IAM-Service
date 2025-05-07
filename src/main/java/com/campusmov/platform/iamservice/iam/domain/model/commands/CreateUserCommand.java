package com.campusmov.platform.iamservice.iam.domain.model.commands;

import com.campusmov.platform.iamservice.iam.domain.model.entities.Role;

import java.util.List;

public record CreateUserCommand(String email, List<String> roleName) {
    public CreateUserCommand {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        if (roleName == null || roleName.isEmpty()) {
            throw new IllegalArgumentException("Role ID cannot be null or empty");
        }
    }
}
