package kg.nurtelecom.internlabs.actionsconfigurator.api.service.user.impl;


import jakarta.annotation.Nullable;
import kg.nurtelecom.internlabs.actionsconfigurator.api.repository.user.UserRepository;
import kg.nurtelecom.internlabs.actionsconfigurator.api.service.user.UserService;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.request.user.UserRequest;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.request.user.UserRequestUpdate;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.response.user.UserResponse;
import kg.nurtelecom.internlabs.actionsconfigurator.common.entity.user.User;
import kg.nurtelecom.internlabs.actionsconfigurator.common.exception.user.UserAuthenticationException;
import kg.nurtelecom.internlabs.actionsconfigurator.common.exception.user.UserNotFoundException;
import kg.nurtelecom.internlabs.actionsconfigurator.common.mapper.UserMapper;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
  * The UserServiceImpl class implements the UserService interface and provides
  * methods for managing user data.
  */
@Order(1)
@Service("userService")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    UserMapper userMapper;

    @Override
    public UserResponse create(@NonNull UserRequest userRequest) {
        return userMapper.entityToResponse(
                create(
                        userMapper.requestToEntity(userRequest),
                        getCurrentUser()
                )
        );
    }

    @Override
    public UserResponse findById(@NonNull Long id) {
        return userMapper.entityToResponse(
                find(id)
        );
    }

    @Override
    public List<UserResponse> findAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::entityToResponse)
                .toList();
    }

    @Override
    public UserResponse updateById(@NonNull Long id, @NonNull UserRequestUpdate userRequestUpdate) {
        return userMapper.entityToResponse(
                update(
                        id,
                        userMapper.requestToEntity(userRequestUpdate),
                        getCurrentUser()
                )
        );
    }

    @Override
    public UserResponse deleteById(@NonNull Long id) {
        return userMapper.entityToResponse(
                delete(
                        id,
                        getCurrentUser()
                )
        );
    }

    @Override
    public Long count() {
        return userRepository.count();
    }

    @Override
    public Boolean existsById(@NonNull Long id) {
        return userRepository.existsById(id);
    }

    /**
     * A method to find a User by their email address.
     * @param email The email address of the User to find.
     * @return An Optional containing the User with the specified email if it exists, otherwise an empty Optional.
     * @throws UserNotFoundException When an error occurs, this class is displayed
     */
    @Override
    public UserResponse findByEmail(@NonNull String email) {
        return userMapper.entityToResponse(
                find(email)
        );
    }

    @Override
    public User create(@NonNull User user, @Nullable User currentUser) {
        user.setCreatedBy(currentUser);
        user.setCreatedAt(LocalDateTime.now());

        return userRepository.save(user);
    }

    @Override
    public User find(@NonNull Long id) {
        Optional<User> byEmail = userRepository.findById(id);

        if (byEmail.isPresent()) {
            return byEmail.get();
        } else {
            throw new UserNotFoundException("Cannot find user by provided id: " + id);
        }
    }

    @Override
    public User update(@NonNull Long userId, @NonNull User updatedUser, @Nullable User currentUser) {
        User user = find(userId);

        // TODO: Find way to rewrite this peace of code to make it more understandable
        // check UserUpdateRequest for info about user update
        user.setFirstName(updatedUser.getFirstName());
        user.setLastName(updatedUser.getLastName());
        user.setEmail(updatedUser.getEmail());
        user.setRole(updatedUser.getRole());

        user.setUpdatedBy(currentUser);
        user.setUpdatedAt(LocalDateTime.now());

        return userRepository.save(user);
    }

    @Override
    public User delete(@NonNull Long userId, @Nullable User currentUser) {
        User user = find(userId);

        user.setDeleted(true);
        user.setUpdatedBy(currentUser);
        user.setUpdatedAt(LocalDateTime.now());

        return user;
    }

    /**
     * Returns current user for services. If there is no current user or authentication
     * is not supported then this method throws <code>UserAuthenticationException</code>
     * @return User object of current service call.
     * @throws UserAuthenticationException Thrown when user no authenticated
     * or authentication not supported by this application.
     */
    // TODO: Check if static here is appropriate.
    public User getCurrentUser() throws UserAuthenticationException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser;
        if ((authentication != null) && !(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUsername = authentication.getName();
            Optional<User> byEmailResult = userRepository.findByEmail(currentUsername);

            if (byEmailResult.isPresent()) {
                currentUser = byEmailResult.get();
            } else {
                throw new UserAuthenticationException("Cannot authenticate current user");
            }
        } else {
            throw new UserAuthenticationException("Authentication not supported");
        }
        return currentUser;
    }

    @Override
    public User find(@NonNull String email) {
        Optional<User> byEmail = userRepository.findByEmail(email);

        if (byEmail.isPresent()) {
            return byEmail.get();
        } else {
            throw new UserNotFoundException("Cannot find user by provided email: " + email);
        }
    }
}
