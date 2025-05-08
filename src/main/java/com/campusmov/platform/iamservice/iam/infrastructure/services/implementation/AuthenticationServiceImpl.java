package com.campusmov.platform.iamservice.iam.infrastructure.services.implementation;

import com.campusmov.platform.iamservice.iam.domain.model.aggregates.User;
import com.campusmov.platform.iamservice.iam.domain.model.valueobjects.UserStatus;
import com.campusmov.platform.iamservice.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import com.campusmov.platform.iamservice.iam.infrastructure.services.AuthenticationService;
import com.campusmov.platform.iamservice.iam.infrastructure.model.VerifyUser;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;

    public AuthenticationServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public Optional<User> verifyUser(VerifyUser vUser) {
        Optional<User> optionalUser = userRepository.findByEmail(vUser.getEmail());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getVerificationCodeExpiresAt().isBefore(LocalDateTime.now())) {
                throw new RuntimeException("Verification code has expired");
            }
            if (user.getVerificationCode().equals(vUser.getVerificationCode())) {
                if (user.getStatus() == UserStatus.CREATED) {user.setStatus(UserStatus.ACTIVE);}
                user.setVerificationCode(null);
                user.setVerificationCodeExpiresAt(null);
                userRepository.save(user);
                return Optional.of(user);
            } else {
                throw new RuntimeException("Invalid verification code");
            }
        } else {
            throw new RuntimeException("User not found");
        }
    }

}
