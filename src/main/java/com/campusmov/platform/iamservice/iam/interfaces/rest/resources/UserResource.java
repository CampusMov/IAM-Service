package com.campusmov.platform.iamservice.iam.interfaces.rest.resources;

import com.campusmov.platform.iamservice.iam.domain.model.entities.Role;

import java.util.List;

public record UserResource(String id, String email, String status, List<Role> roles) {
    public UserResource {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("id is required");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("email is required");
        }
        if (roles == null || roles.isEmpty()) {
            throw new IllegalArgumentException("roles is required");
        }
        if (status == null || status.isBlank()) {
            throw new IllegalArgumentException("status is required");
        }
    }
}
