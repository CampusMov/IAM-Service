package com.campusmov.platform.iamservice.iam.interfaces.rest;

import com.campusmov.platform.iamservice.iam.domain.model.commands.CreateUserCommand;
import com.campusmov.platform.iamservice.iam.domain.services.UserCommandService;
import com.campusmov.platform.iamservice.iam.interfaces.rest.resources.CreateUserResource;
import com.campusmov.platform.iamservice.iam.interfaces.rest.resources.UserResource;
import com.campusmov.platform.iamservice.iam.interfaces.rest.transform.CreateUserCommandFromResourceAssembler;
import com.campusmov.platform.iamservice.iam.interfaces.rest.transform.UserResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/registration")
@Tag(name = "Registration", description = "Registration Management Endpoints")
public class RegistrationController {
    private final UserCommandService userCommandService;


    public RegistrationController(UserCommandService userCommandService) {
        this.userCommandService = userCommandService;
    }

    @PostMapping("/create-account")
    @Operation(summary = "Create a new account", description = "Create a new account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Account created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<UserResource> createAccount(@RequestBody CreateUserResource createUserResource) {
        var command = CreateUserCommandFromResourceAssembler.toCommandFromResource(createUserResource);
        var user = userCommandService.handle(command);
        var resource = UserResourceFromEntityAssembler.toResourceFromEntity(user.get());
        return ResponseEntity.ok(resource);
    }
}
