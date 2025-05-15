package com.campusmov.platform.iamservice.iam.application.internal.eventhandlers;

import com.campusmov.platform.iamservice.iam.domain.model.commands.SeedRoleCommand;
import com.campusmov.platform.iamservice.iam.domain.services.RoleCommandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class IamApplicationReadyEventHandler {
    private final RoleCommandService roleCommandService;
    private static final Logger LOGGER = LoggerFactory.getLogger(IamApplicationReadyEventHandler.class);

    public IamApplicationReadyEventHandler(RoleCommandService roleCommandService) {
        this.roleCommandService = roleCommandService;
    }

    @EventListener
    public void onIamApplicationReady(ApplicationReadyEvent event) {loadRolesData(event);}


    private void loadRolesData(ApplicationReadyEvent event) {
        var name = event.getApplicationContext().getId();
        LOGGER.info("Starting to seed roles for {} at {}", name, System.currentTimeMillis());
        var seedRoleCommand = new SeedRoleCommand();
        roleCommandService.handle(seedRoleCommand);
        LOGGER.info("Roles seeded successfully for {} at {}", name, System.currentTimeMillis());
    }


}
