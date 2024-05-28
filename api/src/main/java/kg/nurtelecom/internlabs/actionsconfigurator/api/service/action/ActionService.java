package kg.nurtelecom.internlabs.actionsconfigurator.api.service.action;

import kg.nurtelecom.internlabs.actionsconfigurator.api.service.BaseService;
import kg.nurtelecom.internlabs.actionsconfigurator.api.service.EntityService;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.request.action.ActionRequest;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.response.action.ActionResponse;
import kg.nurtelecom.internlabs.actionsconfigurator.common.entity.action.Action;

import java.time.LocalDateTime;
import java.util.List;

/**
 * The ActionService interface provides methods for managing action data. Extends <code>BaseService</code>.
 */
public interface ActionService extends
        BaseService<ActionResponse, ActionRequest, ActionRequest, Long>,
        EntityService<Action, Long>
{

    /**
     * Retrieves all actions from the system by name.
     * @param actionName Name of the actions.
     * @return The list of all action objects by name.
     */
    List<ActionResponse> findAllByName(String actionName);

    List<ActionResponse> findAllByStartDateAfter(LocalDateTime after);

    /**
     * Counts the total number of items by names.
     * @param actionName Name of actions.
     * @return  The total number of items by names
     */
    Long countByName(String actionName);

    /**
     * Checks if an action with the given name exists
     * @param actionName The name of action to check.
     * @return <code>true</code> if an item with the specified name exists, <code>false</code> otherwise.
     */
    Boolean existsByName(String actionName);

    boolean isActionTypeAndTemplateValid(Action action);

    List<Action> find(LocalDateTime after);
}

