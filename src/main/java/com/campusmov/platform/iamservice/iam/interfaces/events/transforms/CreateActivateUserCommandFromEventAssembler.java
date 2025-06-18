package com.campusmov.platform.iamservice.iam.interfaces.events.transforms;

import com.campusmov.platform.iamservice.iam.domain.model.commands.ActivateUserCommand;
import com.campusmov.platform.iamservice.shared.domain.model.events.ProfileCreatedEvent;

public class CreateActivateUserCommandFromEventAssembler {
    public static ActivateUserCommand toCommandFromEvent(ProfileCreatedEvent event) {
        return ActivateUserCommand.builder()
                .userId(event.getProfileId())
                .build();
    }
}
