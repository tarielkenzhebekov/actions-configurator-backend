package kg.nurtelecom.internlabs.actionsconfigurator.api.service;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service template. Contains all CRUD methods including "all" variant.
 * @param <R> Type of returning values.
 * @param <C> Type of creation requests.
 * @param <U> Type of update requests.
 * @param <ID> Type of IDs.
 */
@Service
public interface BaseService<R, C, U, ID> {

    /**
     * Creates a new resource in the system.
     * @param c The resource to be created.
     * @return The created resource objects.
     */
    R create(C c);

    /**
     * Retrieves resource from the system.
     * @param id The ID of the resource in the system.
     * @return The retried resource object.
     */
    R findById(ID id);

    /**
     * Retrieves all resources from the system.
     * @return The list of retried resource objects.
     */
    List<R> findAll();

    /**
     * Updates existing resource in the system.
     * @param u The resource object with updated information.
     * @return The updated resource object.
     */
    R updateById(ID id, U u);

    /**
     * Deletes resource from the system.
     * @param id The ID of resource to delete.
     * @return The resource that was deleted.
     */
    R deleteById(ID id);

    /**
     * Counts the total number of items.
     * @return The total number of items.
     */
    Long count();

    /**
     * Checks if an resource with the given ID exists.
     * @param id The ID of resource to check.
     * @return <code>true</code> if an item with the specified ID exists, <code>false</code> otherwise.
     */
    Boolean existsById(ID id);
}
