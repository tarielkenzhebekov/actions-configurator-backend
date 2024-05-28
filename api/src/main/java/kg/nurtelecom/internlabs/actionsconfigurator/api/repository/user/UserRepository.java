package kg.nurtelecom.internlabs.actionsconfigurator.api.repository.user;

import kg.nurtelecom.internlabs.actionsconfigurator.common.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * The UserRepository class is responsible for managing actions in the application.
 * It provides methods for retrieving, creating, updating, and deleting actions.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    @Modifying
    @Transactional
    @Query("UPDATE User user SET user.deleted = false WHERE user.id = :id")
    void deleteUser(Long id);

    @Query("SELECT user FROM User user WHERE user.deleted = true")
    List<User> findAllActiveUsers();
}
