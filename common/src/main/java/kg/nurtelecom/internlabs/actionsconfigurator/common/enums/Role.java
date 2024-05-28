package kg.nurtelecom.internlabs.actionsconfigurator.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static kg.nurtelecom.internlabs.actionsconfigurator.common.enums.Permission.ADMINISTRATOR_CREATE;
import static kg.nurtelecom.internlabs.actionsconfigurator.common.enums.Permission.ADMINISTRATOR_DELETE;
import static kg.nurtelecom.internlabs.actionsconfigurator.common.enums.Permission.ADMINISTRATOR_READ;
import static kg.nurtelecom.internlabs.actionsconfigurator.common.enums.Permission.ADMINISTRATOR_UPDATE;
import static kg.nurtelecom.internlabs.actionsconfigurator.common.enums.Permission.MANAGER_CREATE;
import static kg.nurtelecom.internlabs.actionsconfigurator.common.enums.Permission.MANAGER_DELETE;
import static kg.nurtelecom.internlabs.actionsconfigurator.common.enums.Permission.MANAGER_READ;
import static kg.nurtelecom.internlabs.actionsconfigurator.common.enums.Permission.MANAGER_UPDATE;
import static kg.nurtelecom.internlabs.actionsconfigurator.common.enums.Permission.USER_READ;

@RequiredArgsConstructor
public enum Role {
    ADMINISTRATOR(
            Set.of(
                    ADMINISTRATOR_READ,
                    ADMINISTRATOR_UPDATE,
                    ADMINISTRATOR_CREATE,
                    ADMINISTRATOR_DELETE,
                    MANAGER_READ,
                    MANAGER_UPDATE,
                    MANAGER_CREATE,
                    MANAGER_DELETE
            )
    ),
    MANAGER(
            Set.of(
                    MANAGER_READ,
                    MANAGER_UPDATE,
                    MANAGER_CREATE,
                    MANAGER_DELETE
            )
    ),
    USER(
            Set.of(
                    USER_READ
            )
    );

    @Getter
    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities =
                getPermissions()
                        .stream()
                        .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                        .collect(Collectors.toList());

        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
