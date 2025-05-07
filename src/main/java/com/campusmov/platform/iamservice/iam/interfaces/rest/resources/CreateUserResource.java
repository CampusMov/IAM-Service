package com.campusmov.platform.iamservice.iam.interfaces.rest.resources;

import java.util.List;

public record CreateUserResource(String email, List<String> roleName) {
    public CreateUserResource {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        if (roleName == null || roleName.isEmpty()) {
            throw new IllegalArgumentException("Role ID cannot be null or empty");
        }
    }
}
