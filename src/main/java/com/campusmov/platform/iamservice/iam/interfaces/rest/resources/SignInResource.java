package com.campusmov.platform.iamservice.iam.interfaces.rest.resources;

public record SignInResource(String email) {
    public SignInResource {
        if (email == null) {
            throw new IllegalArgumentException("email is required");
        }

    }
}
