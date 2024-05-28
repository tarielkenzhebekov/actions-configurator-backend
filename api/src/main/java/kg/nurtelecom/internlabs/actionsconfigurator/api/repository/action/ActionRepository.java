package kg.nurtelecom.internlabs.actionsconfigurator.api.repository.action;

import kg.nurtelecom.internlabs.actionsconfigurator.common.entity.action.Action;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * The ActionRepository class is responsible for managing actions in the application.
 * It provides methods for retrieving, creating, updating, and deleting actions.
 */
@Repository
public interface ActionRepository extends JpaRepository<Action, Long> {

    List<Action> findAllByName(String name);

    Long countByName(String name);

    Boolean existsByName(String name);

    List<Action> findActionByStartDateAfter(LocalDateTime dateTime);
}
