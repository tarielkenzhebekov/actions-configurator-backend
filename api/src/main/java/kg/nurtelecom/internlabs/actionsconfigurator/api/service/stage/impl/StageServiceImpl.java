package kg.nurtelecom.internlabs.actionsconfigurator.api.service.stage.impl;

import jakarta.annotation.Nullable;
import kg.nurtelecom.internlabs.actionsconfigurator.api.repository.stage.StageRepository;
import kg.nurtelecom.internlabs.actionsconfigurator.api.service.action.ActionService;
import kg.nurtelecom.internlabs.actionsconfigurator.api.service.stage.StageService;
import kg.nurtelecom.internlabs.actionsconfigurator.api.service.user.UserService;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.request.stage.StageRequest;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.response.stage.StageResponse;
import kg.nurtelecom.internlabs.actionsconfigurator.common.entity.stage.Stage;
import kg.nurtelecom.internlabs.actionsconfigurator.common.entity.user.User;
import kg.nurtelecom.internlabs.actionsconfigurator.common.exception.stage.StageNotFoundException;
import kg.nurtelecom.internlabs.actionsconfigurator.common.mapper.StageMapper;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

/**
 * The StageServiceImpl class implements the StageService interface and provides
 * methods for managing stage data.
 */
@Service("stageService")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@DependsOn({
        "actionService",
        "userService"
})
public class StageServiceImpl implements StageService {

    StageRepository stageRepository;

    StageMapper stageMapper;

    ActionService actionService;

    UserService userService;

    /**
     * Creates a new stage in the system.
     * @param stageRequest The stage object to be created.
     * @return The created stage object.
     */
    @Override
    public StageResponse create(@NonNull StageRequest stageRequest) {
        Stage stage = mapToStage_AndSetAction(stageRequest);

        return stageMapper.entityToResponse(
                create(
                        stage,
                        userService.getCurrentUser()
                )
        );
    }

    /**
     * Retrieves a stage from the system based on the given ID.
     * @param stageId The unique identifier of the stage to be retrieved.
     * @return The retrieved stage object.
     * @throws StageNotFoundException When an error occurs, this class is displayed
     */
    @Override
    public StageResponse findById(@NonNull Long stageId) {
        return stageMapper.entityToResponse(
                find(stageId)
        );
    }

    /**
     * Retrieves all stage from the system.
     * @return The list of all stages objects.
     */
    @Override
    public List<StageResponse> findAll() {
        return stageRepository.findAll()
                .stream()
                .map(stageMapper::entityToResponse)
                .toList();
    }

    /**
     * Updates an existing stage in the system.
     * @param stageId Searches by ID
     * @param stageRequest The user object with updated information.
     * @return The updated stage object.
     */
    @Override
    public StageResponse updateById(@NonNull Long stageId, @NonNull StageRequest stageRequest) {
        Stage stage = mapToStage_AndSetAction(stageRequest);

        return stageMapper.entityToResponse(
                update(
                        stageId,
                        stage,
                        userService.getCurrentUser()
                )
        );
    }

    /**
     * Deletes a stage from the system based on the given ID.
     * @param stageId The unique identifier of the stage to be deleted.
     * @return The deleted resource object.
     */
    @Override
    public StageResponse deleteById(@NonNull Long stageId) {
        return stageMapper.entityToResponse(
                delete(
                        stageId,
                        userService.getCurrentUser()
                )
        );
    }

    /**
     * Retrieves a list of StageResponse objects with the specified start date.
     * @param start The start date to search for.
     * @return A list of StageResponse objects that have the specified start date.
     */
    @Override
    public List<StageResponse> findByStartDate(@NonNull LocalDateTime start) {
        List<Stage> stages = stageRepository.findByStartDate(start);

        return stages.stream()
                .map(stageMapper::entityToResponse)
                .toList();
    }

    /**
     * Retrieves a list of StageResponse objects with the specified end date.
     * @param end The end date to search for.
     * @return A list of StageResponse objects that have the specified end date
     */
    @Override
    public List<StageResponse> findByEndDate(@NonNull LocalDateTime end) {
        List<Stage> stages = stageRepository.findByEndDate(end);

        return stages.stream()
                .map(stageMapper::entityToResponse)
                .toList();
    }

    /**
     * Retrieves a list of StageResponse objects with the specified start and end date.
     * @param start The start date to search for.
     * @param end The end date to search for.
     * @return A list of StageResponse objects that have the specified start and end date.
     */
    @Override
    public List<StageResponse> findByDateRange(@NonNull LocalDateTime start, @NonNull LocalDateTime end) {
        List<Stage> stages = stageRepository.findByDateRange(start, end);

        return stages.stream()
                .map(stageMapper::entityToResponse)
                .toList();
    }

    @Override
    public Long count() {
        return stageRepository.count();
    }

    @Override
    public Boolean existsById(Long stageId) {
        return stageRepository.existsById(stageId);
    }

    @Override
    public Stage create(@NonNull Stage stage, @Nullable User currentUser) {
        stage.setCreatedBy(currentUser);
        stage.setCreatedAt(LocalDateTime.now());

        return stageRepository.save(stage);
    }

    @Override
    public Stage find(@NonNull Long stageId) {
        Optional<Stage> byId = stageRepository.findById(stageId);

        if (byId.isPresent()) {
            return byId.get();
        }
        throw new StageNotFoundException("Cannot find stage by provided id: " + stageId);
    }

    @Override
    public Stage update(@NonNull Long stageId, @NonNull Stage updatedStage, @Nullable User currentUser) {
        Stage stage = find(stageId);

        stage.setStartDate(updatedStage.getStartDate());
        stage.setEndDate(updatedStage.getEndDate());
        stage.setTicketLimit(updatedStage.getTicketLimit());
        stage.setPromocodeLimit(updatedStage.getPromocodeLimit());
        stage.setAction(updatedStage.getAction());

        stage.setUpdatedBy(currentUser);
        stage.setUpdatedAt(LocalDateTime.now());

        return stageRepository.save(stage);
    }

    @Override
    public Stage delete(@NonNull Long stageId, @Nullable User currentUser) {
        Stage stage = find(stageId);

        stage.setDeleted(true);
        stage.setUpdatedBy(currentUser);
        stage.setUpdatedAt(LocalDateTime.now());

        return stageRepository.save(stage);
    }

    @Override
    public boolean isStagesDatesChained(@NonNull List<Stage> stages) {
        Iterator<Stage> iterator = stages.iterator();

        Stage previousStage = iterator.next();
        while (iterator.hasNext()) {
            Stage currentStage = iterator.next();

            if (! previousStage.getEndDate()
                    .isEqual(
                            currentStage.getStartDate()
                    )) {
                return false;
            }

            previousStage = currentStage;
        }

        return true;
    }

    private Stage mapToStage_AndSetAction(StageRequest stageRequest) {
        Stage stage = stageMapper.requestToEntity(stageRequest);

        if (stageRequest.actionId() != null) {
            stage.setAction(
                    actionService.find(stageRequest.actionId())
            );
        }

        return stage;
    }
}
