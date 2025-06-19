package com.campusmov.platform.iamservice.iam.domain.model.commands;

import lombok.Builder;

@Builder
public record ActivateUserCommand(String userId) {
    public ActivateUserCommand {
        if (userId == null || userId.isBlank()) {
            throw new IllegalArgumentException("User ID cannot be null or empty");
        }
    }
}
