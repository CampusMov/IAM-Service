package com.campusmov.platform.iamservice.iam.domain.model.entities;

import com.campusmov.platform.iamservice.iam.domain.model.aggregates.User;
import com.campusmov.platform.iamservice.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.campusmov.platform.iamservice.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
public class Role extends AuditableAbstractAggregateRoot<Role> {

    @Getter
    @Setter
    @NotBlank
    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;

}
