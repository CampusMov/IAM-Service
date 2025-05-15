package com.campusmov.platform.iamservice.iam.application.internal.commandservices;


import com.campusmov.platform.iamservice.iam.domain.model.aggregates.User;
import com.campusmov.platform.iamservice.iam.domain.model.commands.CreateUserCommand;
import com.campusmov.platform.iamservice.iam.domain.services.UserCommandService;
import com.campusmov.platform.iamservice.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import com.campusmov.platform.iamservice.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import jakarta.transaction.Transactional;
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
        if (userRepository.existsByEmail(command.email())) {
            return userRepository.findByEmail(command.email());
        }
//        var roles = command.roleName().stream()
//                .map(roleName -> roleRepository.findByName(roleName)
//                        .orElseThrow(() -> new RuntimeException("Role with name " + roleName + " not found")))
//                .toList();

        var user = new User(command);

        Random random = new Random();
        int code = random.nextInt(900000) + 100000;
        user.setVerificationCode(String.valueOf(code));
        user.setVerificationCodeExpiresAt(LocalDateTime.now().plusMinutes(5));
        user.setVerifyAccountExpiresAt(LocalDateTime.now().plusMinutes(15));

        userRepository.save(user);
        return Optional.of(user);
    }

    @Scheduled(fixedRate = 60000)
    @Transactional
    public void deleteExpiredAccounts() {
        userRepository.deleteAllByVerifyAccountExpiresAtBefore(LocalDateTime.now());
    }
}
