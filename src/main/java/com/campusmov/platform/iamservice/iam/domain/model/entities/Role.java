package com.campusmov.platform.iamservice.iam.domain.model.entities;

import com.campusmov.platform.iamservice.iam.domain.model.aggregates.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
public class Role {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @NotBlank
    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;

}
