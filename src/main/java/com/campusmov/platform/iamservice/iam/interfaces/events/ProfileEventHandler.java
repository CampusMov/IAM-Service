package com.campusmov.platform.iamservice.iam.interfaces.events;

import com.campusmov.platform.iamservice.iam.domain.services.UserCommandService;
import com.campusmov.platform.iamservice.iam.interfaces.events.transforms.CreateActivateUserCommandFromEventAssembler;
import com.campusmov.platform.iamservice.shared.domain.model.events.ProfileCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileEventHandler {
    private final UserCommandService userCommandService;

    @EventListener
    public void handleProfileCreatedEvent(ProfileCreatedEvent event) {
        var activeUserCommand = CreateActivateUserCommandFromEventAssembler.toCommandFromEvent(event);
        userCommandService.handle(activeUserCommand);
    }
}
