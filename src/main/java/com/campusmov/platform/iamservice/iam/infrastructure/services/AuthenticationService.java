package com.campusmov.platform.iamservice.iam.infrastructure.services;

import com.campusmov.platform.iamservice.iam.domain.model.aggregates.User;
import com.campusmov.platform.iamservice.iam.infrastructure.model.VerifyUser;

import java.util.Optional;

public interface AuthenticationService {

    Optional<User> verifyUser(VerifyUser input);
}
