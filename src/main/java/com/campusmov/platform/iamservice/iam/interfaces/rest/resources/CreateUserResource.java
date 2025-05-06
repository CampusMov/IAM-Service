package com.campusmov.platform.iamservice.iam.interfaces.rest.resources;

import java.util.List;

public record CreateUserResource(String email, String password, List<Long> roleId) {
    public CreateUserResource {
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
