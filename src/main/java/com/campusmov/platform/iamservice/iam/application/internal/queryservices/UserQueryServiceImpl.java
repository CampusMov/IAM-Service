package com.campusmov.platform.iamservice.iam.application.internal.queryservices;

import com.campusmov.platform.iamservice.iam.domain.model.aggregates.User;
import com.campusmov.platform.iamservice.iam.domain.model.queries.GetUserByIdQuery;
import com.campusmov.platform.iamservice.iam.domain.services.UserQueryService;
import com.campusmov.platform.iamservice.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserQueryServiceImpl implements UserQueryService {
    private final UserRepository userRepository;

    public UserQueryServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> handle(GetUserByIdQuery query) {
        return userRepository.findById(query.userId());
    }
}
