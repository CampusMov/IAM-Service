package com.campusmov.platform.iamservice.iam.domain.services;

import com.campusmov.platform.iamservice.iam.domain.model.commands.SeedRoleCommand;

public interface RoleCommandService {
    void handle(SeedRoleCommand command);
}
