package kg.nurtelecom.internlabs.actionsconfigurator.api.repository.token;

import kg.nurtelecom.internlabs.actionsconfigurator.common.entity.auth.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * The TokenRepository class is responsible for managing tokens in the application.
 * It provides methods for retrieving, creating, updating, and deleting tokens.
 */
public interface TokenRepository extends JpaRepository<Token, Integer> {
    @Query("""
        SELECT t FROM Token t INNER JOIN User u\s
        ON t.user.id = u.id\s
        WHERE u.id = :id AND (t.revoked = false AND t.expired = false)
    """)
    List<Token> findAllValidTokensByUser(Long id);

    Optional<Token> findByToken(String token);
}
