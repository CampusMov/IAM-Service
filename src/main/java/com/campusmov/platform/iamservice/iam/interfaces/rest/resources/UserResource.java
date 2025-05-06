package com.campusmov.platform.iamservice.iam.interfaces.rest.resources;

import com.campusmov.platform.iamservice.iam.domain.model.entities.Role;

import java.util.List;

public record UserResource(Long id, String email, List<Role> roles) {
    public UserResource {
        if (id == null) {
            throw new IllegalArgumentException("id is required");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("email is required");
        }
        if (roles == null || roles.isEmpty()) {
            throw new IllegalArgumentException("roles is required");
        }
    }
}
