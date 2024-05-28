package kg.nurtelecom.internlabs.actionsconfigurator.api.controller.action;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kg.nurtelecom.internlabs.actionsconfigurator.api.service.action.ActionService;
import kg.nurtelecom.internlabs.actionsconfigurator.api.util.ResponseMessage;
import kg.nurtelecom.internlabs.actionsconfigurator.api.util.ResultCode;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.request.action.ActionRequest;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.response.action.ActionResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * The ActionController class represents the controller for performing CRUD operations on the database.
 * It handles actions related to creating, reading, updating, and deleting resources. Most of CRUD methods are
 * declared in <code>BaseController</code> generic class such as create, find, findAll, update, delete, count
 */
@RestController
@RequestMapping("/action")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Tag(name = "Action")
@DependsOn("actionService")
public class ActionController {

    ActionService actionService;

    /**
     * Create a new resource in the database.
     *
     * @param actionRequest The resource object to create.
     * @return The created resource object.
     */
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create new action", description = "Action creation")
    public ResponseMessage<ActionResponse> create(@Valid @RequestBody ActionRequest actionRequest) {
        return new ResponseMessage<>(
                actionService.create(actionRequest),
                ResultCode.SUCCESS
        );
    }

    /**
     * Retrieve a resource from the database based on the given ID.
     *
     * @param actionId The id of resource object to retrieve.
     * @return The retrieved resource object.
     */
    @GetMapping("/find/{actionId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get action by id", description = "Get an id action")
    public ResponseMessage<ActionResponse> findById(@PathVariable Long actionId) {
        return new ResponseMessage<>(
                actionService.findById(actionId),
                ResultCode.SUCCESS
        );
    }

    /**
     * Retrieves all resources from database.
     *
     * @return The list of all actions.
     */
    @GetMapping("/find/all")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get a list of all actions", description = "Get a list of all actions")
    public ResponseMessage<List<ActionResponse>> findAll(@RequestParam(value = "name", required = false) String actionName,
                                                         @RequestParam(value = "after", required = false) LocalDateTime after) {
        return new ResponseMessage<>(
                actionName == null
                        ? after == null
                        ? actionService.findAll()
                        : actionService.findAllByStartDateAfter(after)
                        : actionService.findAllByName(actionName),
                ResultCode.SUCCESS
        );
    }

    /**
     * Update an existing resource in the database.
     *
     * @param actionId      Searches by ID.
     * @param actionRequest The resource object with updated information.
     * @return The updated resource object.
     */
    @PutMapping("/update/{actionId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Change action by id", description = "Сhange of action on the id")
    public ResponseMessage<ActionResponse> updateById(@PathVariable Long actionId, @Valid @RequestBody ActionRequest actionRequest) {
        return new ResponseMessage<>(
                actionService.updateById(actionId, actionRequest),
                ResultCode.SUCCESS
        );
    }

    /**
     * Delete a resource from the database based on the given ID.
     *
     * @param actionId Searches by ID.
     * @return The deleted resource object.
     */
    @DeleteMapping("/delete/{actionId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Delete action by id", description = "Delete of action on the id")
    public ResponseMessage<ActionResponse> deleteById(@PathVariable Long actionId) {
        return new ResponseMessage<>(
                actionService.deleteById(actionId),
                ResultCode.SUCCESS
        );
    }

    /**
     * Returns count of all items in database.
     *
     * @return If none 0 positive integer otherwise.
     */
    @GetMapping("/count")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Action count", description = "Action count")
    public ResponseMessage<Long> count() {
        return new ResponseMessage<>(
                actionService.count(),
                ResultCode.SUCCESS
        );
    }

    @GetMapping("/count/{actionName}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Count action by name", description = "Count action by name")
    public ResponseMessage<Long> countByName(@PathVariable String actionName) {
        return new ResponseMessage<>(
                actionService.countByName(actionName),
                ResultCode.SUCCESS
        );
    }

    /**
     * Checks if item by provided ID is exist in database.
     *
     * @param actionId The ID of item to check.
     * @return Returns <code>true</code> if item exist within database.
     * If item do not exist then returns <code>false</code>.
     */
    @GetMapping("/exists/{actionId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Check action by Id", description = "Check action by Id")
    public ResponseMessage<Boolean> existsById(@PathVariable Long actionId) {
        return new ResponseMessage<>(
                actionService.existsById(actionId),
                ResultCode.SUCCESS
        );
    }

    @GetMapping("/exists/{actionName}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Check action by name", description = "Check action by name")
    public ResponseMessage<Boolean> existsByName(@PathVariable String actionName) {
        return new ResponseMessage<>(
                actionService.existsByName(actionName),
                ResultCode.SUCCESS
        );
    }
}