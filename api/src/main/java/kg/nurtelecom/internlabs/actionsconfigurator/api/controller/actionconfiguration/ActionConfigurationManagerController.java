package kg.nurtelecom.internlabs.actionsconfigurator.api.controller.actionconfiguration;


import jakarta.validation.Valid;
import kg.nurtelecom.internlabs.actionsconfigurator.api.service.actionconfiguration.ActionConfiguratorManagerService;
import kg.nurtelecom.internlabs.actionsconfigurator.api.util.ResponseMessage;
import kg.nurtelecom.internlabs.actionsconfigurator.api.util.ResultCode;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.request.actionconfiguration.ActionConfigurationRequest;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.response.actionconfiguration.ActionConfigurationResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/action-configuration")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@DependsOn("actionConfiguratorManagerService")
public class ActionConfigurationManagerController {

    @Lazy
    ActionConfiguratorManagerService actionConfiguratorManagerService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseMessage<ActionConfigurationResponse> create(@Valid @RequestBody ActionConfigurationRequest actionConfigurationRequest) {
        return new ResponseMessage<>(
                actionConfiguratorManagerService.create(actionConfigurationRequest),
                ResultCode.SUCCESS
        );
    }

    @GetMapping("/find/{actionId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseMessage<ActionConfigurationResponse> find(@PathVariable("actionId") Long actionId) {
        return new ResponseMessage<>(
                actionConfiguratorManagerService.findById(actionId),
                ResultCode.SUCCESS
        );
    }

    @PutMapping("/update/{actionId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseMessage<ActionConfigurationResponse> update(@PathVariable Long actionId,
                                                               @Valid @RequestBody ActionConfigurationRequest actionConfigurationRequest) {
        return new ResponseMessage<>(
                actionConfiguratorManagerService.updateById(actionId, actionConfigurationRequest),
                ResultCode.SUCCESS
        );
    }

    @DeleteMapping("/delete/{actionId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseMessage<ActionConfigurationResponse> delete(@PathVariable Long actionId) {
        return new ResponseMessage<>(
                actionConfiguratorManagerService.deleteById(actionId),
                ResultCode.SUCCESS
        );
    }

}
