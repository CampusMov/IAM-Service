package com.campusmov.platform.iamservice.iam.domain.model.aggregates;

import com.campusmov.platform.iamservice.iam.domain.model.commands.CreateUserCommand;
import com.campusmov.platform.iamservice.iam.domain.model.entities.Role;
import com.campusmov.platform.iamservice.iam.domain.model.valueobjects.UserStatus;
import com.campusmov.platform.iamservice.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class User extends AuditableAbstractAggregateRoot<User> {

    @NotBlank
    @Column(unique = true)
    private String email;


    @NotNull
    private UserStatus status;

    @NotNull
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

    private String verificationCode;

    private LocalDateTime verificationCodeExpiresAt;

    private LocalDateTime verifyAccountExpiresAt;

    public User() {
    }

    public User(CreateUserCommand command) {
        this.email = command.email();
        this.status = UserStatus.NOT_VERIFIED;
        this.roles = new ArrayList<>();
    }

    public List<String> getRoleNames() {
        return roles.stream()
                .map(Role::getRoleName)
                .toList();
    }

    public void activate() {
        this.status = UserStatus.ACTIVE;
        this.verificationCode = null;
        this.verificationCodeExpiresAt = null;
        this.verifyAccountExpiresAt = null;
    }

}
