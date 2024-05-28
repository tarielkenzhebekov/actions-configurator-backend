package kg.nurtelecom.internlabs.actionsconfigurator.api.controller.auth;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import kg.nurtelecom.internlabs.actionsconfigurator.api.service.auth.AuthenticationService;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.request.user.UserRequest;
import kg.nurtelecom.internlabs.actionsconfigurator.common.exception.user.UserAuthenticationException;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.request.auth.AuthenticationRequest;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.response.auth.AuthenticationResponse;
import kg.nurtelecom.internlabs.actionsconfigurator.api.util.ResponseMessage;
import kg.nurtelecom.internlabs.actionsconfigurator.api.util.ResultCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * The AuthenticationController class handles incoming requests related to user authentication.
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication" , description = "User Authorization")
public class AuthenticationController {
    private final AuthenticationService service;

    /**
     *
     * @param userRequest
     * @return
     */
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "All users can registration", description = "Users registration")
    public ResponseMessage<AuthenticationResponse> register(@Valid @RequestBody UserRequest userRequest)
            throws UserAuthenticationException {
        return new ResponseMessage<>(
                service.register(userRequest),
                ResultCode.SUCCESS
        );
    }

    /**
     *
     * @param request
     * @return
     */
    @PostMapping("/authenticate")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "All users can authenticate" , description = " Users authorization")
    public ResponseEntity<AuthenticationResponse> authenticate(@Valid @RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    /**
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @PostMapping("/refresh-token")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "All users can get refresh tokens", description = "Users refresh token")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        service.refreshToken(request, response);
    }
}
