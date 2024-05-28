package kg.nurtelecom.internlabs.actionsconfigurator.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    ADMINISTRATOR_READ("administrator:read"),
    ADMINISTRATOR_UPDATE("administrator:update"),
    ADMINISTRATOR_CREATE("administrator:create"),
    ADMINISTRATOR_DELETE("administrator:delete"),
    MANAGER_READ("manager:read"),
    MANAGER_UPDATE("manager:update"),
    MANAGER_CREATE("manager:create"),
    MANAGER_DELETE("manager:delete"),
    USER_READ("user:read");


    @Getter
    private final String permission;
}
