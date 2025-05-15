package com.campusmov.platform.iamservice.iam.interfaces.rest.resources;

import java.util.regex.Pattern;

public record CreateUserResource(String email) {
    public CreateUserResource {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }

        boolean emailPattern = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$").matcher(email).matches();

        boolean universityDomainPattern = email.endsWith(".edu.pe");

        if (!emailPattern || !universityDomainPattern) {
            throw new IllegalArgumentException("Invalid email format");
        }


    }
}
