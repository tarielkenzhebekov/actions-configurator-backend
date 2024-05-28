package kg.nurtelecom.internlabs.actionsconfigurator.common.dto.request.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import kg.nurtelecom.internlabs.actionsconfigurator.common.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {
    @NotBlank(message = "User email cannot be blank or null.")
    @Email(
            regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$",
            message = "User email '${validatedValue}' must be valid email address."
    )
    @Size(
            max = User.EMAIL_LENGTH,
            message = "User email '${validatedValue} cannot contain more than {max} symbols."
    )
    private String email;

    @NotBlank(message = "User password cannot be blank or null.")
    @Size(
            min = 6,
            max = User.PASSWORD_LENGTH,
            message = "User password '${validatedValue}' cannot contain more than {max} and less then {min} symbols."
    )
    private String password;
}
