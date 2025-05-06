package com.campusmov.platform.iamservice.iam.domain.model.commands;

import com.campusmov.platform.iamservice.iam.domain.model.entities.Role;

import java.util.List;

public record CreateUserCommand(String email, String password, List<Long> roleId) {
    public CreateUserCommand {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        if (roleId == null || roleId.isEmpty()) {
            throw new IllegalArgumentException("Role ID cannot be null or empty");
        }
    }
}
