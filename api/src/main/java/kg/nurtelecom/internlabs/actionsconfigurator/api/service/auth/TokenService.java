package kg.nurtelecom.internlabs.actionsconfigurator.api.service.auth;

import kg.nurtelecom.internlabs.actionsconfigurator.api.repository.token.TokenRepository;
import kg.nurtelecom.internlabs.actionsconfigurator.common.entity.auth.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * The TokenService class provides methods for token generation, validation, and revocation.
 */
@Service
@RequiredArgsConstructor
public class TokenService {
    private final TokenRepository tokenRepository;

    public List<Token> findAllValidTokensByUser(Long id) {
        return tokenRepository.findAllValidTokensByUser(id);
    }

    public Optional<Token> findByToken(String token) {
        return tokenRepository.findByToken(token);
    }

    public Token save(Token token) {
        return tokenRepository.save(token);
    }

    public List<Token> saveAll(List<Token> validUserTokens) {
        return tokenRepository.saveAll(validUserTokens);
    }
}