package com.campusmov.platform.iamservice.iam.application.internal.commandservices;


import com.campusmov.platform.iamservice.iam.domain.model.aggregates.User;
import com.campusmov.platform.iamservice.iam.domain.model.commands.ActivateUserCommand;
import com.campusmov.platform.iamservice.iam.domain.model.commands.CreateUserCommand;
import com.campusmov.platform.iamservice.iam.domain.services.UserCommandService;
import com.campusmov.platform.iamservice.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import com.campusmov.platform.iamservice.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@RequiredArgsConstructor
@Service
public class UserCommandServiceImpl implements UserCommandService {
    private final UserRepository userRepository;

    @Override
    public Optional<User> handle(CreateUserCommand command) {
        Integer code = generateVerificationCode();

        if (userRepository.existsByEmail(command.email())) {
            var existingUser = userRepository.findByEmail(command.email());
            if (existingUser.isPresent()) {
                var user = existingUser.get();
                user.setVerificationCode(String.valueOf(code));
                user.setVerificationCodeExpiresAt(LocalDateTime.now().plusMinutes(5));
                userRepository.save(user);
                return Optional.of(user);
            }
            return Optional.empty();
        }

        var user = new User(command);
        user.setVerificationCode(String.valueOf(code));
        user.setVerificationCodeExpiresAt(LocalDateTime.now().plusMinutes(1));
        user.setVerifyAccountExpiresAt(LocalDateTime.now().plusMinutes(15));

        userRepository.save(user);
        return Optional.of(user);
    }

    @Override
    public void handle(ActivateUserCommand command) {
        User existingUser = userRepository.findById(command.userId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        existingUser.activate();
        try {
            userRepository.save(existingUser);
        } catch (Exception e) {
            throw new RuntimeException("Error saving user", e);
        }
    }

    @Scheduled(fixedRate = 60000)
    @Transactional
    public void deleteExpiredAccounts() {
        userRepository.deleteAllByVerifyAccountExpiresAtBefore(LocalDateTime.now());
    }

    private boolean isVerificationCodeValid(User user, String code) {
        return user.getVerificationCode() != null &&
                user.getVerificationCode().equals(code) &&
                user.getVerificationCodeExpiresAt() != null &&
                LocalDateTime.now().isBefore(user.getVerificationCodeExpiresAt());
    }

    private Integer generateVerificationCode() {
        Random random = new Random();
        return random.nextInt(900000) + 100000;
    }
}
