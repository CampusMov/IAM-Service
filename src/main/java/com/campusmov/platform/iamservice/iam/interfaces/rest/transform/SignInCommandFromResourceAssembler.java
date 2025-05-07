package com.campusmov.platform.iamservice.iam.interfaces.rest.transform;

import com.campusmov.platform.iamservice.iam.domain.model.commands.SignInCommand;
import com.campusmov.platform.iamservice.iam.interfaces.rest.resources.SignInResource;

public class SignInCommandFromResourceAssembler {
    public static SignInCommand toCommandFromResource(SignInResource resource) {
        return new SignInCommand(resource.email(), resource.password());
    }
}
