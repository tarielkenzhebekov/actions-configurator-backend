package kg.nurtelecom.internlabs.actionsconfigurator.api.startup.seed;

import kg.nurtelecom.internlabs.actionsconfigurator.api.repository.user.UserRepository;
import kg.nurtelecom.internlabs.actionsconfigurator.api.service.auth.AuthenticationService;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.request.user.UserRequest;
import kg.nurtelecom.internlabs.actionsconfigurator.common.entity.user.User;
import kg.nurtelecom.internlabs.actionsconfigurator.common.enums.Role;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static kg.nurtelecom.internlabs.actionsconfigurator.common.enums.Role.ADMINISTRATOR;

/**
 * Creates seed bean for creating super admin if super admin not present in database.
 */
@Component
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class SuperAdminSeed implements CommandLineRunner {

    AuthenticationService authenticationService;
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    /**
     * Runs <code>CommandLineRunner</code> on application startup.
     * This <code>CommandLineRunner</code> then calls <code>AuthenticationService</code>
     * to register user if it does not already exist or just updates user in repository.
     */
    @Override
    public void run(String... args) {
        Optional<User> byEmail = userRepository.findByEmail("admin@nurtelecom.kg");
        if (byEmail.isPresent()) {
            updateAdmin(byEmail.get());
        } else {
            createAdmin();
        }
    }

    private void updateAdmin(User user) {
        user.setFirstName(Admin.firstName);
        user.setLastName(Admin.lastName);
        user.setEmail(Admin.email);
        user.setPassword(
                passwordEncoder.encode(Admin.password)
        );
        user.setRole(Admin.role);

        userRepository.save(user);
    }

    private void createAdmin() {
        UserRequest userRequest = UserRequest.builder()
                .firstName(Admin.firstName)
                .lastName(Admin.lastName)
                .email(Admin.email)
                .password(Admin.password)
                .role(Admin.role)
                .build();
        authenticationService.register(userRequest);
    }

    private static final class Admin {
        private static final String firstName = "Default";
        private static final String lastName = "Admin";
        private static final String email = "admin@nurtelecom.kg";
        private static final String password = "qwe123!@#";
        private static final Role role = ADMINISTRATOR;
    }
}
