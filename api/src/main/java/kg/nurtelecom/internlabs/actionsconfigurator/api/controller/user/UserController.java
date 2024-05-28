package kg.nurtelecom.internlabs.actionsconfigurator.api.controller.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kg.nurtelecom.internlabs.actionsconfigurator.api.service.user.UserService;
import kg.nurtelecom.internlabs.actionsconfigurator.api.util.ResponseMessage;
import kg.nurtelecom.internlabs.actionsconfigurator.api.util.ResultCode;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.request.user.UserRequestUpdate;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.response.user.UserResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The UserController class represents the controller for performing CRUD operations on the database.
 * It provides methods for creating, reading, updating, and deleting resources.
 */
@RestController
@RequestMapping("/user")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Tag(name = "User")
@DependsOn("userService")
public class UserController {

    UserService userService;

    /**
     * Retrieve a resource from the database based on the given ID.
     * @param userId The unique identifier of the resource to be retrieved.
     * @return The retrieved resource object.
     */
    @GetMapping("/find/{userId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get user by id", description = "Get user by id")
    public ResponseMessage<UserResponse> findById(@PathVariable Long userId) {
        return new ResponseMessage<>(
                userService.findById(userId),
                ResultCode.SUCCESS
        );
    }

    /**
     * Finds all registered users in application.
     * @return <code>ResponseMessage</code> that contains list of all registered users.
     */
    @GetMapping("/find/all")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get a list of all user", description = "Get a list of all user")
    public ResponseMessage<List<UserResponse>> findAll() {
        return new ResponseMessage<>(
                userService.findAll(),
                ResultCode.SUCCESS
        );
    }

    /**
     * Update an existing resource in the database.
     * @param userId Searches by ID
     * @param userRequest The resource object with updated information.
     * @return The updated resource object.
     */
    @PutMapping("/update/{userId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Change user by id", description = "Change user by id")
    public ResponseMessage<UserResponse> updateById(@PathVariable Long userId,
                                                    @Valid @RequestBody UserRequestUpdate userRequest) {

        return new ResponseMessage<>(
                userService.updateById(userId, userRequest),
                ResultCode.SUCCESS
        );
    }

    /**
     * Delete a resource from the database based on the given ID.
     * @param userId Searches by ID.
     * @return The deleted resource object.
     */
    @DeleteMapping("/delete/{userId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Delete user by id", description = "Delete user by id")
    public ResponseMessage<UserResponse> deleteUser(@PathVariable Long userId) {
        return new ResponseMessage<>(
                userService.deleteById(userId),
                ResultCode.SUCCESS
        );
    }

}

