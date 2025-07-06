package com.campusmov.platform.iamservice.iam.domain.services;

import com.campusmov.platform.iamservice.iam.domain.model.aggregates.User;
import com.campusmov.platform.iamservice.iam.domain.model.queries.GetUserByIdQuery;

import java.util.Optional;

public interface UserQueryService {
    Optional<User> handle(GetUserByIdQuery query);
}
