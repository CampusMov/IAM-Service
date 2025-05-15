package com.campusmov.platform.iamservice.iam.interfaces.rest;

import com.campusmov.platform.iamservice.iam.domain.services.UserCommandService;
import com.campusmov.platform.iamservice.iam.infrastructure.services.EmailService;
import com.campusmov.platform.iamservice.iam.infrastructure.model.Email;
import com.campusmov.platform.iamservice.iam.interfaces.rest.resources.CreateUserResource;
import com.campusmov.platform.iamservice.iam.interfaces.rest.transform.CreateUserCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/registration")
@Tag(name = "registration", description = "Registration Management Endpoints")
public class RegistrationController {
    private final UserCommandService userCommandService;
    private final EmailService emailService;


    public RegistrationController(UserCommandService userCommandService, EmailService emailService) {
        this.userCommandService = userCommandService;
        this.emailService = emailService;
    }

    @PostMapping("/sign-up")
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

}
