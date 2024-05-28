package kg.nurtelecom.internlabs.actionsconfigurator.api.controller.rule;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kg.nurtelecom.internlabs.actionsconfigurator.api.service.rule.RuleService;
import kg.nurtelecom.internlabs.actionsconfigurator.api.util.ResponseMessage;
import kg.nurtelecom.internlabs.actionsconfigurator.api.util.ResultCode;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.request.rule.RuleRequest;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.response.rule.RuleResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The RuleController class represents the controller for performing CRUD operations on the database.
 * It provides methods for creating, reading, updating, and deleting resources.
 */
@RestController
@RequestMapping("/rule")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Tag(name = "Rule")
@DependsOn("ruleService")
public class RuleController {

    RuleService ruleService;

    /**
     * Create a new resource in the database.
     * @param ruleRequest The resource object to be created
     * @return The created resource object.
     */
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create new rule", description = "Create new rule")
    public ResponseMessage<RuleResponse> create(@Valid @RequestBody RuleRequest ruleRequest) {
        return new ResponseMessage<>(
                ruleService.create(ruleRequest),
                ResultCode.SUCCESS
        );
    }

    /**
     * Retrieve a resource from the database based on the given ID.
     * @param ruleId The unique identifier of the resource to be retrieved.
     * @return The retrieved resource object.
     */
    @GetMapping("/find/{ruleId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get rule by id", description = "Get rule by id")
    public ResponseMessage<RuleResponse> findById(@PathVariable Long ruleId) {
        return new ResponseMessage<>(
                ruleService.findById(ruleId),
                ResultCode.SUCCESS
        );
    }

    @GetMapping("/find/all")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get a list of all rule", description = "Get a list of all rule")
    public ResponseMessage<List<RuleResponse>> findAll(@RequestParam(value = "min-sum", required = false) Double minSum,
                                                       @RequestParam(value = "max-sum", required = false) Double maxSum) {

        return new ResponseMessage<>(
                ruleService.findAll(minSum, maxSum),
                ResultCode.SUCCESS
        );
    }

    /**
     * Update an existing resource in the database.
     * @param ruleId Searches by ID
     * @param ruleRequest The resource object with updated information.
     * @return The updated resource object.
     */
    @PutMapping("/update/{ruleId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Change rule by id", description = "Change rule by id")
    public ResponseMessage<RuleResponse> updateById(@PathVariable Long ruleId,
                                                    @Valid @RequestBody RuleRequest ruleRequest) {

        return new ResponseMessage<>(
                ruleService.updateById(ruleId, ruleRequest),
                ResultCode.SUCCESS
        );
    }

    /**
     * Delete a resource from the database based on the given ID.
     * @param ruleId Searches by ID.
     * @return The deleted resource object.
     */
    @DeleteMapping("/delete/{ruleId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Delete rule by id", description = "Delete rule by id")
    public ResponseMessage<RuleResponse> deleteById(@PathVariable Long ruleId) {
        return new ResponseMessage<>(
                ruleService.deleteById(ruleId),
                ResultCode.SUCCESS
        );
    }

    @GetMapping("/count")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Rule count", description = "Rule count")
    public ResponseMessage<Long> count() {
        return new ResponseMessage<>(
                ruleService.count(),
                ResultCode.SUCCESS
        );
    }

    @GetMapping("/exists/{ruleId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Check rule by Id", description = "Check rule by Id")
    public ResponseMessage<Boolean> existsById(@PathVariable Long ruleId) {
        return new ResponseMessage<>(
                ruleService.existsById(ruleId),
                ResultCode.SUCCESS
        );
    }

}

