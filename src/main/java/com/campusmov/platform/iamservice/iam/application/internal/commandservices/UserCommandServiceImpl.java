package com.campusmov.platform.iamservice.iam.application.internal.commandservices;


import com.campusmov.platform.iamservice.iam.domain.model.aggregates.User;
import com.campusmov.platform.iamservice.iam.domain.model.commands.CreateUserCommand;
import com.campusmov.platform.iamservice.iam.domain.model.commands.SignInCommand;
import com.campusmov.platform.iamservice.iam.domain.services.UserCommandService;
import com.campusmov.platform.iamservice.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import com.campusmov.platform.iamservice.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class UserCommandServiceImpl implements UserCommandService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserCommandServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public Optional<User> handle(CreateUserCommand command) {
        if (userRepository.existsByEmail(command.email())) throw new RuntimeException("User " + command.email() + " already exists");

        var roles = command.roleName().stream()
                .map(roleName -> roleRepository.findByName(roleName)
                        .orElseThrow(() -> new RuntimeException("Role with name " + roleName + " not found")))
                .toList();

        var user = new User(command.email(), roles);

        Random random = new Random();
        int code = random.nextInt(900000) + 100000;
        user.setVerificationCode(String.valueOf(code));
        user.setVerificationCodeExpiresAt(LocalDateTime.now().plusMinutes(5));

        userRepository.save(user);
        return Optional.of(user);

    }

    @Override
    public Optional<User> handle(SignInCommand command) {
        var user = userRepository.findByEmail(command.email());
        if (user.isEmpty()) throw new RuntimeException("User " + command.email() + " not found");

        return user;
    }

    @Scheduled(fixedRate = 60000)
    public void deleteExpiredAccounts() {
            userRepository.deleteAllByVerifyAccountExpiresAt(LocalDateTime.now());
    }


}
