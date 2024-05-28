package kg.nurtelecom.internlabs.actionsconfigurator.api.controller;

import jakarta.validation.Valid;
import kg.nurtelecom.internlabs.actionsconfigurator.api.service.BaseService;
import kg.nurtelecom.internlabs.actionsconfigurator.api.util.ResponseMessage;
import kg.nurtelecom.internlabs.actionsconfigurator.api.util.ResultCode;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Controller template. Contains basic methods to create, update, find and delete methods.
 * @param <R> Type of returning values.
 * @param <C> Type of creation requests.
 * @param <U> Type of update requests.
 * @param <ID> Type of IDs.
 */
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@AllArgsConstructor
public abstract class BaseController<R, C, U, ID> {

    BaseService<R, C, U, ID> service;

    /**
     * Create a new resource in the database.
     * @param c The resource object to create.
     * @return The created resource object.
     */
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseMessage<R> create(@Valid @RequestBody C c) {
        return new ResponseMessage<>(
                service.create(c),
                ResultCode.SUCCESS
        );
    }

    /**
     * Retrieve a resource from the database based on the given ID.
     * @param id The id of resource object to retrieve.
     * @return The retrieved resource object.
     */
    @GetMapping("/find/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseMessage<R> findById(@PathVariable ID id) {
        return new ResponseMessage<>(
                service.findById(id),
                ResultCode.SUCCESS
        );
    }

    /**
     * Update an existing resource in the database.
     * @param id Searches by ID.
     * @param u The resource object with updated information.
     * @return The updated resource object.
     */
    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseMessage<R> updateById(@PathVariable ID id, @Valid @RequestBody U u) {
        return new ResponseMessage<>(
                service.updateById(id, u),
                ResultCode.SUCCESS
        );
    }

    /**
     * Delete a resource from the database based on the given ID.
     * @param id Searches by ID.
     * @return The deleted resource object.
     */
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseMessage<R> deleteById(@PathVariable ID id) {
        return new ResponseMessage<>(
                service.deleteById(id),
                ResultCode.SUCCESS
        );
    }

    /**
     * Returns count of all items in database.
     * @return If none 0 positive integer otherwise.
     */
    @GetMapping("/count")
    @ResponseStatus(HttpStatus.OK)
    public ResponseMessage<Long> count() {
        return new ResponseMessage<>(
                service.count(),
                ResultCode.SUCCESS
        );
    }

    /**
     * Checks if item by provided ID is exist in database.
     * @param id The ID of item to check.
     * @return Returns <code>true</code> if item exist within database.
     * If item do not exist then returns <code>false</code>.
     */
    @GetMapping("/exists/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseMessage<Boolean> existsById(@PathVariable ID id) {
        return new ResponseMessage<>(
                service.existsById(id),
                ResultCode.SUCCESS
        );
    }
}
