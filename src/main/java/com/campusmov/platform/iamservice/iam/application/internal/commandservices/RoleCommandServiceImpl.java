package com.campusmov.platform.iamservice.iam.application.internal.commandservices;

import com.campusmov.platform.iamservice.iam.domain.model.commands.SeedRoleCommand;
import com.campusmov.platform.iamservice.iam.domain.model.entities.Role;
import com.campusmov.platform.iamservice.iam.domain.services.RoleCommandService;
import com.campusmov.platform.iamservice.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import org.springframework.stereotype.Service;


@Service
public class RoleCommandServiceImpl implements RoleCommandService{
    private final RoleRepository roleRepository;

    public RoleCommandServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void handle(SeedRoleCommand command) {
        if (!roleRepository.existsByName("DRIVER")) {
            Role driverRole = new Role();
            driverRole.setName("DRIVER");
            roleRepository.save(driverRole);
        }

        if (!roleRepository.existsByName("PASSENGER")) {
            Role passengerRole = new Role();
            passengerRole.setName("PASSENGER");
            roleRepository.save(passengerRole);
        }
    }
}
