package com.campusmov.platform.iamservice.iam.domain.services;

import com.campusmov.platform.iamservice.iam.domain.model.aggregates.User;
import com.campusmov.platform.iamservice.iam.domain.model.commands.ActivateUserCommand;
import com.campusmov.platform.iamservice.iam.domain.model.commands.CreateUserCommand;

import java.util.Optional;

public interface UserCommandService {

    Optional<User> handle(CreateUserCommand command);
    void handle(ActivateUserCommand command);
}
