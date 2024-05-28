package kg.nurtelecom.internlabs.actionsconfigurator.common.entity.user;

import jakarta.persistence.*;
import kg.nurtelecom.internlabs.actionsconfigurator.common.entity.BaseEntity;
import kg.nurtelecom.internlabs.actionsconfigurator.common.entity.CommonConstants;
import kg.nurtelecom.internlabs.actionsconfigurator.common.enums.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;


@Entity
@Table(name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@SuperBuilder
public class User extends BaseEntity implements UserDetails {

    public static final int FIRST_NAME_LENGTH = CommonConstants.FIRST_NAME_LENGTH;
    public static final int LAST_NAME_LENGTH = CommonConstants.LAST_NAME_LENGTH;
    public static final int EMAIL_LENGTH = CommonConstants.EMAIL_LENGTH;
    public static final int PASSWORD_LENGTH = CommonConstants.PASSWORD_LENGTH;

    /**
     * Entity id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_generator")
    @GenericGenerator(
            name = "sequence_generator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "users_seq"),
                    @Parameter(name = "initial_value", value = "1000")
            })
    @Column(name = "id")
    Long id;

    @Column(name = "first_name", length = FIRST_NAME_LENGTH, nullable = false)
    String firstName;

    @Column(name = "last_name", length = LAST_NAME_LENGTH, nullable = false)
    String lastName;

    @Column(name = "email", length = EMAIL_LENGTH, unique = true, nullable = false)
    String email;

    @Column(name = "password", length = PASSWORD_LENGTH, nullable = false)
    String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return ! getDeleted();
    }
}