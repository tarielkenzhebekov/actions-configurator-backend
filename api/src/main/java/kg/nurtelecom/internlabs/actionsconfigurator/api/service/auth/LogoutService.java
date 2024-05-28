package kg.nurtelecom.internlabs.actionsconfigurator.api.service.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kg.nurtelecom.internlabs.actionsconfigurator.common.entity.auth.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

/**
 * The LogoutService class provides methods for user logout and token revocation.
 */
@Service
@RequiredArgsConstructor
public class LogoutService implements LogoutHandler {
    private final TokenService tokenService;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        final String authHeader = request.getHeader(AUTHORIZATION);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }

        final String jwtToken = authHeader.substring(7);

        Optional<Token> savedToken = tokenService.findByToken(jwtToken);
        if (savedToken.isPresent()) {
            Token token = savedToken.get();
            token.setRevoked(true);
            token.setExpired(true);
            tokenService.save(token);
        }
    }
}
