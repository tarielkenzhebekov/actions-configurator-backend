package kg.nurtelecom.internlabs.actionsconfigurator.common.dto.request.user;

import jakarta.annotation.Nullable;
import kg.nurtelecom.internlabs.actionsconfigurator.common.entity.user.User;
import kg.nurtelecom.internlabs.actionsconfigurator.common.enums.Role;

import jakarta.validation.constraints.*;
import lombok.Builder;

// TODO: Check user and user request update
@Builder
public record UserRequest(
        @Nullable
        Long id,

        @NotBlank(message = "First name cannot be null or blank")
        @Size(
                max = User.FIRST_NAME_LENGTH,
                message = "First name must be at most {max} characters long"
        )
        String firstName,

        @NotBlank(message = "Last name cannot be null or blank")
        @Size(
                max = User.LAST_NAME_LENGTH,
                message = "Last name must be at most {max} characters long"
        )
        String lastName,

        @NotBlank(message = "Email cannot be null or blank")
        @Size(
                max = User.EMAIL_LENGTH,
                message = "Email must be at most {max} characters long"
        )
        @Pattern(
                regexp = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}",
                message = "Invalid email format"
        )
        String email,

        @NotBlank(message = "Password cannot be null or blank")
        @Size(
                min = 6,
                max = User.PASSWORD_LENGTH,
                message = "Password must be between {min} and {max} characters"
        )
        @Pattern(
                regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d@$!%*?&]+$",
                message = "Password must contain at least one letter and one digit, and may include special characters"
        )
        String password,

        @NotNull(message = "Role cannot be null")
        Role role
) {
}
