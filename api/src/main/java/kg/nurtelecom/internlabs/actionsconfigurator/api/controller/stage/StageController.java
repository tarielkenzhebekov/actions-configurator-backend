package kg.nurtelecom.internlabs.actionsconfigurator.api.controller.stage;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kg.nurtelecom.internlabs.actionsconfigurator.api.service.stage.StageService;
import kg.nurtelecom.internlabs.actionsconfigurator.api.util.ResponseMessage;
import kg.nurtelecom.internlabs.actionsconfigurator.api.util.ResultCode;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.request.stage.StageRequest;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.response.stage.StageResponse;
import kg.nurtelecom.internlabs.actionsconfigurator.common.exception.MethodNotSupportedException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * The StageController class represents the controller for performing CRUD operations on the database.
 * It provides methods for creating, reading, updating, and deleting resources.
 */
@RestController
@RequestMapping("/stage")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Tag(name = "Stage")
@DependsOn("stageService")
public class StageController {

    StageService stageService;

    /**
     * Create a new resource in the database.
     * @param stageRequest The resource object to be created
     * @return The created resource object.
     */
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create new stage", description = "Create new stage")
    public ResponseMessage<StageResponse> create(@Valid @RequestBody StageRequest stageRequest) {
        return new ResponseMessage<>(
                stageService.create(stageRequest),
                ResultCode.SUCCESS
        );
    }

    /**
     * Retrieve a resource from the database based on the given ID.
     * @param stageId The unique identifier of the resource to be retrieved.
     * @return The retrieved resource object.
     */
    @GetMapping("/find/{stageId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get stage by id", description = "Get stage by id")
    public ResponseMessage<StageResponse> findById(@PathVariable Long stageId) {
        return new ResponseMessage<>(
                stageService.findById(stageId),
                ResultCode.SUCCESS
        );
    }

    /**
     *
     * @return
     * @throws MethodNotSupportedException
     */
    @GetMapping("/find/all")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get a list of all stage", description = "Get a list of all stage")
    public ResponseMessage<List<StageResponse>> findAll() throws MethodNotSupportedException {
        return new ResponseMessage<>(
                stageService.findAll(),
                ResultCode.SUCCESS
        );
    }

    @GetMapping("find/start")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get stage by start date", description = "Get stage by start date")
    public ResponseMessage<List<StageResponse>> findByStartDate(@RequestParam("date") LocalDateTime date) throws MethodNotSupportedException {
        return new ResponseMessage<>(
                stageService.findByStartDate(date),
                ResultCode.SUCCESS
        );
    }

    @GetMapping("find/end")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get stage by end date", description = "Get stage by end date")
    public ResponseMessage<List<StageResponse>> findByEndDate(@RequestParam("date") LocalDateTime date) throws MethodNotSupportedException {
        return new ResponseMessage<>(
                stageService.findByEndDate(date),
                ResultCode.SUCCESS
        );
    }

    @GetMapping("find/date-range")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get stage by range date", description = "Get stage by range date")
    public ResponseMessage<List<StageResponse>> findByDateRange(@RequestParam("start") LocalDateTime start,
                                                                @RequestParam("end") LocalDateTime end) throws MethodNotSupportedException {
        return new ResponseMessage<>(
                stageService.findByDateRange(start, end),
                ResultCode.SUCCESS
        );
    }

    /**
     * Update an existing resource in the database.
     * @param stageId Searches by ID
     * @param stageRequest The resource object with updated information.
     * @return The updated resource object.
     */
    @PutMapping("/update/{stageId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Change stage by id", description = "Change stage by id")
    public ResponseMessage<StageResponse> updateById(@PathVariable Long stageId,
                                                     @Valid @RequestBody StageRequest stageRequest) {

        return new ResponseMessage<>(
                stageService.updateById(stageId, stageRequest),
                ResultCode.SUCCESS
        );
    }

    /**
     * Delete a resource from the database based on the given ID.
     * @param stageId Searches by ID
     * @return The deleted resource object.
     */
    @DeleteMapping("/delete/{stageId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Delete stage by id", description = "Delete stage by id")
    public ResponseMessage<StageResponse> deleteById(@PathVariable Long stageId) {
        return new ResponseMessage<>(
                stageService.deleteById(stageId),
                ResultCode.SUCCESS
        );
    }
}

