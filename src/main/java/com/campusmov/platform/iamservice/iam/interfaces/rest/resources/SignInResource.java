package com.campusmov.platform.iamservice.iam.interfaces.rest.resources;

public record SignInResource(String email, String password) {
    public SignInResource {
        if (email == null) {
            throw new IllegalArgumentException("email is required");
        }
        if (password == null) {
            throw new IllegalArgumentException("password is required");
        }
    }
}
