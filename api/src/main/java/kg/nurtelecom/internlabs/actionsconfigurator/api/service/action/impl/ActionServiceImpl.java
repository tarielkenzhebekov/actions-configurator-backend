package kg.nurtelecom.internlabs.actionsconfigurator.api.service.action.impl;

import kg.nurtelecom.internlabs.actionsconfigurator.api.repository.action.ActionRepository;
import kg.nurtelecom.internlabs.actionsconfigurator.api.service.action.ActionService;
import kg.nurtelecom.internlabs.actionsconfigurator.api.service.promocode.PromocodeService;
import kg.nurtelecom.internlabs.actionsconfigurator.api.service.ticket.TicketService;
import kg.nurtelecom.internlabs.actionsconfigurator.api.service.user.UserService;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.request.action.ActionRequest;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.response.action.ActionResponse;
import kg.nurtelecom.internlabs.actionsconfigurator.common.entity.action.Action;
import kg.nurtelecom.internlabs.actionsconfigurator.common.entity.user.User;
import kg.nurtelecom.internlabs.actionsconfigurator.common.enums.ActionType;
import kg.nurtelecom.internlabs.actionsconfigurator.common.exception.action.ActionNotFoundException;
import kg.nurtelecom.internlabs.actionsconfigurator.common.exception.user.UserAuthenticationException;
import kg.nurtelecom.internlabs.actionsconfigurator.common.mapper.ActionMapper;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

// TODO: Check all javadoc in this file.
/**
 * The ActionServiceImpl class implements the ActionService interface and provides
 * methods for managing action data.
 */
@Service("actionService")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Setter
@DependsOn({
        "userService"
})
public class ActionServiceImpl implements ActionService {

    ActionRepository actionRepository;

    ActionMapper actionMapper;

    UserService userService;

    @Lazy
    @NonFinal
    TicketService ticketService;

    @Lazy
    @NonFinal
    PromocodeService promocodeService;

    /**
     * Creates a new action in the system.
     * @param actionRequest The action object to be created.
     * @return The created action object.
     * @throws UserAuthenticationException When an error occurs, this class is displayed
     */
    @Override
    public ActionResponse create(@NonNull ActionRequest actionRequest) {
        Action action = mapToAction_AndSetTicketOrPromocode(actionRequest);

        return actionMapper.entityToResponse(
                create(
                        action,
                        userService.getCurrentUser()
                )
        );
    }

    /**
     * Retrieves an action from the system based on the given ID.
     * @param id The unique identifier of the action to be retrieved.
     * @return The retrieved action object.
     * @throws ActionNotFoundException throws when action do not exist in repository
     */
    @Override
    public ActionResponse findById(@NonNull Long id) {
        return actionMapper.entityToResponse(
                find(id)
        );
    }

    /**
     * Retrieves all actions from the system.
     * @return The list of all actions objects.
     */
    @Override
    public List<ActionResponse> findAll() {
        return actionRepository.findAll()
                .stream()
                .map(actionMapper::entityToResponse)
                .toList();
    }

    /**
     * Retrieves all actions from the system by name.
     * @param name Searches by name.
     * @return The list of all action objects by name.
     */
    @Override
    public List<ActionResponse> findAllByName(@NonNull String name) {
        return actionRepository.findAllByName(name)
                .stream()
                .map(actionMapper::entityToResponse)
                .toList();
    }

    @Override
    public List<ActionResponse> findAllByStartDateAfter(@NonNull LocalDateTime after) {
        return find(after)
                .stream()
                .map(actionMapper::entityToResponse)
                .toList();
    }

    /**
     * Updates an existing action in the system.
     * @param id Searches by ID
     * @param actionRequest The action object with updated information.
     * @return The updated action object.
     * @throws ActionNotFoundException When an error occurs, this class is displayed
     * @throws UserAuthenticationException When an error occurs, this class is displayed
     */
    @Override
    public ActionResponse updateById(@NonNull Long id, @NonNull ActionRequest actionRequest) {
        Action action = mapToAction_AndSetTicketOrPromocode(actionRequest);

        return actionMapper.entityToResponse(
                update(
                        id,
                        action,
                        userService.getCurrentUser()
                )
        );
    }

    /**
     * Deletes a action from the system based on the given ID.
     * @param id The unique identifier of the action to be deleted.
     * @return The deleted resource object.
     * @throws ActionNotFoundException When an error occurs, this class is displayed
     * @throws UserAuthenticationException When an error occurs, this class is displayed
     */
    @Override
    public ActionResponse deleteById(@NonNull Long id) {
        return actionMapper.entityToResponse(
                delete(
                        id,
                        userService.getCurrentUser()
                )
        );
    }

    /**
     * Counts the total number of items.
     * @return The total number of items .
     */
    @Override
    public Long count() {
        return actionRepository.count();
    }

    /**
     * Counts the total number of items by names.
     * @param name  Searches by name.
     * @return The total number of items by names
     */
    @Override
    public Long countByName(@NonNull String name) {
        return actionRepository.countByName(name);
    }

    /**
     * Checks if an item with the given ID exists
     * @param id The ID to check
     * @return true if an item with the specified ID exists, false otherwise.
     */
    @Override
    public Boolean existsById(@NonNull Long id) {
        return actionRepository.existsById(id);
    }

    /**
     * Checks if an item with the given name exists
     * @param name The name to check
     * @return true if an item with the specified name exists, false otherwise.
     */
    @Override
    public Boolean existsByName(@NonNull String name) {
        return actionRepository.existsByName(name);
    }

    @Override
    public boolean isActionTypeAndTemplateValid(@NonNull Action action) {
        return (action.getType() == ActionType.TICKET) && (action.getTicketTemplate() != null) && (action.getPromocodeTemplate() == null)
                || (action.getType() == ActionType.PROMOCODE) && (action.getPromocodeTemplate() != null) && (action.getTicketTemplate() == null);
    }

    @Override
    public Action create(@NonNull Action action, User currentUser) {
        action.setCreatedBy(currentUser);
        action.setCreatedAt(LocalDateTime.now());

        return actionRepository.save(action);
    }

    @Override
    public Action find(@NonNull Long id) {
        Optional<Action> byId = actionRepository.findById(id);

        if (byId.isPresent()) {
            return byId.get();
        }
        throw new ActionNotFoundException("Cannot find action by provided id: " + id);
    }

    @Override
    public List<Action> find(@NonNull LocalDateTime after) {
        return actionRepository.findActionByStartDateAfter(after);
    }

    @Override
    public Action update(@NonNull Long actionId, @NonNull Action updatedAction, User currentUser) {
        Action action = find(actionId);

        action.setName(updatedAction.getName());
        action.setDescription(updatedAction.getDescription());
        action.setType(updatedAction.getType());
        action.setStartDate(updatedAction.getStartDate());
        action.setEndDate(updatedAction.getEndDate());
        action.setTicketTemplate(updatedAction.getTicketTemplate());
        action.setPromocodeTemplate(updatedAction.getPromocodeTemplate());

        action.setUpdatedBy(currentUser);
        action.setUpdatedAt(LocalDateTime.now());

        return actionRepository.save(action);
    }

    @Override
    public Action delete(@NonNull Long actionId, User currentUser) {
        Action action = find(actionId);

        action.setDeleted(true);
        action.setUpdatedBy(currentUser);
        action.setUpdatedAt(LocalDateTime.now());

        return actionRepository.save(action);
    }

    private Action mapToAction_AndSetTicketOrPromocode(@NonNull ActionRequest actionRequest) {
        Action action = actionMapper.requestToEntity(actionRequest);
        Long ticketId = actionRequest.ticketTemplateId();
        Long promocodeId = actionRequest.promocodeTemplateId();

        if (ticketId != null) {
            action.setTicketTemplate(ticketService.find(ticketId));
        } else if (promocodeId != null) {
            action.setPromocodeTemplate(promocodeService.find(promocodeId));
        }
        return action;
    }
}