package com.campusmov.platform.iamservice.iam.interfaces.rest.resources;

public record VerifyUserResource(String email, String code, String roleName) {
}
