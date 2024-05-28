package kg.nurtelecom.internlabs.actionsconfigurator.api.service;

import kg.nurtelecom.internlabs.actionsconfigurator.common.entity.user.User;
import org.springframework.stereotype.Service;

/**
 * Interface for managing of entities.
 * @param <E> Entity type parameter.
 * @param <ID> ID type parameter.
 */
@Service
public interface EntityService<E, ID> {

    /**
     * Saves entity to repository.
     * @param e Entity to create
     * @param currentUser User that creates entity.
     * @return Created entity
     */
    E create(E e, User currentUser);

    /**
     * Finds entity in repository.
     * @param id ID of entity in repository.
     * @return Found entity.
     */
    E find(ID id);

    /**
     * Updated entity in repository.
     * @param id ID of entity to update.
     * @param e Entity with updated data.
     * @param currentUser User that updates entity.
     * @return Updated entity.
     */
    E update(ID id, E e, User currentUser);

    /**
     * Deletes entity from repository.
     * @param id ID of the entity to delete.
     * @param currentUser User that deletes entity.
     * @return Deleted entity.
     */
    E delete(ID id, User currentUser);

}
