package kg.nurtelecom.internlabs.actionsconfigurator.api.service.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kg.nurtelecom.internlabs.actionsconfigurator.api.repository.user.UserRepository;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.request.user.UserRequest;
import kg.nurtelecom.internlabs.actionsconfigurator.common.entity.auth.Token;
import kg.nurtelecom.internlabs.actionsconfigurator.common.entity.user.User;
import kg.nurtelecom.internlabs.actionsconfigurator.common.enums.TokenType;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.request.auth.AuthenticationRequest;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.response.auth.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

/**
 * The AuthenticationService class provides methods for user authentication and token management.
 */
@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;

    // TODO: Check currentUserName
    /**
     * Registers a new user.
     * @param userRequest user The user to register
     * @return A message indicating the result of the registration
     */
    public AuthenticationResponse register(UserRequest userRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = null;
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            currentUsername = authentication.getName();
        }
        User user = User.builder()
                .firstName(userRequest.firstName())
                .lastName(userRequest.lastName())
                .email(userRequest.email())
                .password(passwordEncoder.encode(userRequest.password()))
                .role(userRequest.role())
                .createdAt(LocalDateTime.now(ZoneId.systemDefault()))
                .deleted(false)
                .build();
        User savedUser = userRepository.save(user);
        String accessToken = jwtService.generateAccessToken(savedUser);
        String refreshToken = jwtService.generateRefreshToken(savedUser);
        saveUserToken(accessToken, savedUser);

        return AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    /**
     *  Authenticates a user
     * @param request
     * @return
     */
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        final User user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(accessToken, user);

        return AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    /**
     * Saves the user token in the system.
     * @param jwtToken
     * @param user
     */
    private void saveUserToken(String jwtToken, User user) {
        Token token = Token.builder()
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .user(user)
                .build();
        tokenService.save(token);
    }

    /**
     * Revokes all tokens for a user.
     * @param user The user for whom tokens are revoked
     */
    private void revokeAllUserTokens(User user) {
        List<Token> validUserTokens = tokenService.findAllValidTokensByUser(user.getId());
        if (validUserTokens.isEmpty()) {
            return;
        }
        validUserTokens.forEach(t -> {
            t.setExpired(true);
            t.setRevoked(true);
        });

        tokenService.saveAll(validUserTokens);
    }

    /**
     * Refreshes the user's token.
     * @param request
     * @param response
     * @throws IOException
     */
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader(AUTHORIZATION);
        final String userEmail;
        final String refreshToken;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }

        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            User user = userRepository.findByEmail(userEmail).orElseThrow();
            final boolean isTokenValid = jwtService.validateToken(refreshToken, user);
            if (isTokenValid) {
                String accessToken = jwtService.generateAccessToken(user);
                revokeAllUserTokens(user);
                saveUserToken(accessToken, user);
                AuthenticationResponse authResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }
}
