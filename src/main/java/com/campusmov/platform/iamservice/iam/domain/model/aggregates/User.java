package com.campusmov.platform.iamservice.iam.domain.model.aggregates;

import com.campusmov.platform.iamservice.iam.domain.model.entities.Role;
import com.campusmov.platform.iamservice.iam.domain.model.valueobjects.UserStatus;
import com.campusmov.platform.iamservice.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;

@Entity
public class User extends AuditableAbstractAggregateRoot<User> {

    @Getter
    @NotBlank
    @Column(unique = true)
    private String email;


    @NotNull
    private UserStatus status;

    @Getter
    @NotNull
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

    public User() {
    }

    public User(String email, List<Role> roles) {
        this.email = email;
        this.status = UserStatus.CREATED;
        this.roles = roles;
    }

}
