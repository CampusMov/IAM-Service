package com.campusmov.platform.iamservice.iam.interfaces.rest;

import com.campusmov.platform.iamservice.iam.domain.services.UserCommandService;
import com.campusmov.platform.iamservice.iam.infrastructure.services.AuthenticationService;
import com.campusmov.platform.iamservice.iam.infrastructure.services.EmailService;
import com.campusmov.platform.iamservice.iam.interfaces.rest.resources.VerifyUserResource;
import com.campusmov.platform.iamservice.iam.infrastructure.model.VerifyUser;
import com.campusmov.platform.iamservice.iam.interfaces.rest.resources.UserResource;
import com.campusmov.platform.iamservice.iam.interfaces.rest.transform.UserResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/login")
@Tag(name = "login", description = "Login Management Endpoints")
public class LoginController {
    private final EmailService emailService;
    private final UserCommandService userCommandService;
    private final AuthenticationService authenticationService;


    public LoginController(EmailService emailService, UserCommandService userCommandService, AuthenticationService authenticationService) {
        this.emailService = emailService;
        this.userCommandService = userCommandService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/verify-account")
    @Operation(summary = "verify your account", description = "verify your account", operationId = "verify-account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully verified"),
            @ApiResponse(responseCode = "404", description = "Incorrect verification code"),
    })
    public ResponseEntity<UserResource> verifyUser(@RequestParam String email, @RequestParam String verificationCode, @RequestParam String role) {
        var verifyUserResource = new VerifyUserResource(email, verificationCode, role);
        var validateCode = new VerifyUser(verifyUserResource);
        var verifiedUser = authenticationService.verifyUser(validateCode);
        return ResponseEntity.ok(UserResourceFromEntityAssembler.toResourceFromEntity(verifiedUser.get()));
    }



}
