package com.campusmov.platform.iamservice.iam.interfaces.rest;

import com.campusmov.platform.iamservice.iam.domain.model.queries.GetUserByIdQuery;
import com.campusmov.platform.iamservice.iam.domain.services.UserCommandService;
import com.campusmov.platform.iamservice.iam.domain.services.UserQueryService;
import com.campusmov.platform.iamservice.iam.infrastructure.services.EmailService;
import com.campusmov.platform.iamservice.iam.infrastructure.model.Email;
import com.campusmov.platform.iamservice.iam.interfaces.rest.resources.CreateUserResource;
import com.campusmov.platform.iamservice.iam.interfaces.rest.resources.UserResource;
import com.campusmov.platform.iamservice.iam.interfaces.rest.transform.CreateUserCommandFromResourceAssembler;
import com.campusmov.platform.iamservice.iam.interfaces.rest.transform.UserResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth/institutional-email-verification")
@Tag(name = "Authentication", description = "Authentication Management Endpoints")
public class RegistrationController {
    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;
    private final EmailService emailService;


    public RegistrationController(UserCommandService userCommandService, UserQueryService userQueryService, EmailService emailService) {
        this.userCommandService = userCommandService;
        this.userQueryService = userQueryService;
        this.emailService = emailService;
    }

    @PostMapping("")
    @Operation(summary = "register account", description = "Register account", operationId = "sign-up")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Account created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<Void>  createAccount(@RequestParam String email) {
        var createUserResource = new CreateUserResource(email);
        var command = CreateUserCommandFromResourceAssembler.toCommandFromResource(createUserResource);
        var user = userCommandService.handle(command);
        var verificationEmail = new Email(user.get().getEmail(), "Verification code", user.get().getVerificationCode());
        emailService.sendEmail(verificationEmail);
        return ResponseEntity.ok().build();
    }

    @GetMapping("{id}")
    @Operation(summary = "get user", description = "Get user by id", operationId = "get-user-by-id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<UserResource> getUserById(@PathVariable String id) {
        var query = new GetUserByIdQuery(id);
        var user = userQueryService.handle(query);
        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user.get());
        return ResponseEntity.ok(userResource);
    }





}
