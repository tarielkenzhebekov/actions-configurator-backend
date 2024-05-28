package kg.nurtelecom.internlabs.actionsconfigurator.api.service.user;

import kg.nurtelecom.internlabs.actionsconfigurator.api.service.BaseService;
import kg.nurtelecom.internlabs.actionsconfigurator.api.service.EntityService;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.request.user.UserRequest;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.request.user.UserRequestUpdate;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.response.user.UserResponse;
import kg.nurtelecom.internlabs.actionsconfigurator.common.entity.user.User;

/**
 * The UserService interface provides methods for managing user data.
 */
public interface UserService extends
        BaseService<UserResponse, UserRequest, UserRequestUpdate, Long>,
        EntityService<User, Long>
{

    /**
     * A method to find a User by their email address.
     * @param email The email address of the User to find.
     * @return An Optional containing the User with the specified email if it exists, otherwise an empty Optional.
     */
    UserResponse findByEmail(String email);

    /**
     * Retrieves current authenticated user.
     * @return current user.
     */
    User getCurrentUser();

    User find(String email);

}
