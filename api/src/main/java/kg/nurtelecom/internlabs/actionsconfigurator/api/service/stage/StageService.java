package kg.nurtelecom.internlabs.actionsconfigurator.api.service.stage;

import kg.nurtelecom.internlabs.actionsconfigurator.api.service.BaseService;
import kg.nurtelecom.internlabs.actionsconfigurator.api.service.EntityService;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.request.stage.StageRequest;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.response.stage.StageResponse;
import kg.nurtelecom.internlabs.actionsconfigurator.common.entity.stage.Stage;

import java.time.LocalDateTime;
import java.util.List;

/**
 * The StageService interface provides methods for managing stage data.
 */
public interface StageService extends
        BaseService<StageResponse, StageRequest, StageRequest, Long>,
        EntityService<Stage, Long>
{

    /**
     * Retrieves a list of StageResponse objects with the specified start date.
     * @param start The start date to search for.
     * @return A list of StageResponse objects that have the specified start date.
     */
    List<StageResponse> findByStartDate(LocalDateTime start);

    /**
     * Retrieves a list of StageResponse objects with the specified end date.
     * @param end The end date to search for.
     * @return A list of StageResponse objects that have the specified end date.
     */
    List<StageResponse> findByEndDate(LocalDateTime end);

    /**
     * Retrieves a list of StageResponse objects with the specified start and end date.
     * @param start The start date to search for.
     * @param end The end date to search for.
     * @return A list of StageResponse objects that have the specified start and end date.
     */
    List<StageResponse> findByDateRange(LocalDateTime start, LocalDateTime end);

    boolean isStagesDatesChained(List<Stage> stages);

}
