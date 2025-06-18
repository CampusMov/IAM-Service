package com.campusmov.platform.iamservice.iam.infrastructure.brokers.kafka;

import com.campusmov.platform.iamservice.shared.domain.model.events.ProfileCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
@RequiredArgsConstructor
public class AppointmentEventSource {
    private final ApplicationEventPublisher applicationEventPublisher;

    @Bean
    public Consumer<ProfileCreatedEvent> profileCreatedEvent() {
        return applicationEventPublisher::publishEvent;
    }
}
