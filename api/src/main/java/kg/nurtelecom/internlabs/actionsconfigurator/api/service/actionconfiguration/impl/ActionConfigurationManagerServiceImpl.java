package kg.nurtelecom.internlabs.actionsconfigurator.api.service.actionconfiguration.impl;

import jakarta.annotation.Nullable;
import kg.nurtelecom.internlabs.actionsconfigurator.api.service.action.ActionService;
import kg.nurtelecom.internlabs.actionsconfigurator.api.service.actionconfiguration.ActionConfiguration;
import kg.nurtelecom.internlabs.actionsconfigurator.api.service.actionconfiguration.ActionConfiguratorManagerService;
import kg.nurtelecom.internlabs.actionsconfigurator.api.service.cronjob.CronJobService;
import kg.nurtelecom.internlabs.actionsconfigurator.api.service.promocode.PromocodeService;
import kg.nurtelecom.internlabs.actionsconfigurator.api.service.rule.RuleService;
import kg.nurtelecom.internlabs.actionsconfigurator.api.service.stage.StageService;
import kg.nurtelecom.internlabs.actionsconfigurator.api.service.ticket.TicketService;
import kg.nurtelecom.internlabs.actionsconfigurator.api.service.user.UserService;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.request.actionconfiguration.ActionConfigurationRequest;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.request.actionconfiguration.StageConfigurationRequest;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.response.action.ActionResponse;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.response.actionconfiguration.ActionConfigurationResponse;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.response.actionconfiguration.StageConfigurationResponse;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.response.rule.RuleResponse;
import kg.nurtelecom.internlabs.actionsconfigurator.common.entity.action.Action;
import kg.nurtelecom.internlabs.actionsconfigurator.common.entity.promocode.Promocode;
import kg.nurtelecom.internlabs.actionsconfigurator.common.entity.rule.Rule;
import kg.nurtelecom.internlabs.actionsconfigurator.common.entity.stage.Stage;
import kg.nurtelecom.internlabs.actionsconfigurator.common.entity.ticket.Ticket;
import kg.nurtelecom.internlabs.actionsconfigurator.common.entity.user.User;
import kg.nurtelecom.internlabs.actionsconfigurator.common.enums.ActionType;
import kg.nurtelecom.internlabs.actionsconfigurator.common.exception.MethodNotSupportedException;
import kg.nurtelecom.internlabs.actionsconfigurator.common.exception.actionconfiguration.ActionConfigurationNotValidException;
import kg.nurtelecom.internlabs.actionsconfigurator.common.mapper.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service("actionConfiguratorManagerService")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@AllArgsConstructor
@DependsOn({
        "actionService",
        "stageService",
        "ruleService",
        "ticketService",
        "promocodeService",
        "userService",
        "cronJobService"
})
public class ActionConfigurationManagerServiceImpl implements ActionConfiguratorManagerService {

    ActionService actionService;
    StageService stageService;
    RuleService ruleService;
    TicketService ticketService;
    PromocodeService promocodeService;
    UserService userService;
    CronJobService cronJobService;

    ActionMapper actionMapper;
    StageMapper stageMapper;
    RuleMapper ruleMapper;
    TicketMapper ticketMapper;
    PromocodeMapper promocodeMapper;
    UserMapper userMapper;

    @Override
    public ActionConfigurationResponse create(@NonNull ActionConfigurationRequest actionConfigurationRequest) {
        User currentUser = userService.getCurrentUser();

        ActionConfiguration actionConfiguration = prepareActionConfiguration(actionConfigurationRequest);

        if (isActionConfigurationValid(actionConfiguration)) {
            actionConfiguration = create(actionConfiguration, currentUser);
            return convertActionConfigurationToResponse(actionConfiguration, currentUser);
        } else {
            throw new ActionConfigurationNotValidException("Action is not valid");
        }
    }

    @Override
    public ActionConfigurationResponse findById(Long actionId) {
        throw new MethodNotSupportedException("This method not supported yet.");
    }

    @Override
    public List<ActionConfigurationResponse> findAllByStartDateAfter(LocalDateTime after) {
        throw new MethodNotSupportedException("This method not supported yet.");
    }

    @Override
    public List<ActionConfigurationResponse> findAll() {
        throw new MethodNotSupportedException("This method not supported yet.");
    }

    @Override
    public ActionConfigurationResponse updateById(Long actionId, ActionConfigurationRequest actionConfigurationRequest) {
        throw new MethodNotSupportedException("This method not supported yet.");
    }

    @Override
    public ActionConfigurationResponse deleteById(Long actionId) {
        throw new MethodNotSupportedException("This method not supported yet.");
    }

    @Override
    public Long count() {
        throw new MethodNotSupportedException("This method not supported yet.");
    }

    @Override
    public Boolean existsById(Long aLong) {
        throw new MethodNotSupportedException("This method not supported yet.");
    }

    @Override
    public ActionConfiguration create(ActionConfiguration actionConfiguration, User currentUser) {
        createTicketOrPromocode_AndSetToAction(actionConfiguration, currentUser);
        actionConfiguration.setAction(actionService.create(actionConfiguration.getAction(), currentUser));
        createStages(actionConfiguration, currentUser);
        return actionConfiguration;
    }

    @Override
    public List<ActionConfiguration> find() {
        throw new MethodNotSupportedException("This method not supported yet.");
    }

    @Override
    public ActionConfiguration find(Long id) {
        throw new MethodNotSupportedException("This method not supported yet.");
    }

    @Override
    public ActionConfiguration update(Long actionId, ActionConfiguration updatedActionConfiguration, User currentUser) {
        throw new MethodNotSupportedException("This method not supported yet.");
    }

    @Override
    public ActionConfiguration delete(Long actionId, User currentUser) {
        throw new MethodNotSupportedException("This method not supported yet.");
    }

    @Override
    public List<ActionConfiguration> find(LocalDateTime after) {
        throw new MethodNotSupportedException("This method not supported yet.");
    }

    private ActionConfiguration prepareActionConfiguration(ActionConfigurationRequest actionConfigurationRequest) {
        List<StageConfigurationRequest> stageConfigurationRequests = actionConfigurationRequest.getStages();

        List<ActionConfiguration.StageWrapper> stageWrappers = new ArrayList<>();
        for (StageConfigurationRequest request : stageConfigurationRequests) {
            stageWrappers.add(new ActionConfiguration.StageWrapper(
                    stageMapper.requestToEntity(
                            request.getStage()
                    ),
                    ruleMapper.requestsListToEntitiesList(
                            request.getRules()
                    )
            ));
        }

        return new ActionConfiguration(
                actionMapper.requestToEntity(
                        actionConfigurationRequest.getAction()
                ),
                actionConfigurationRequest.getTicketTemplate() != null
                        ? ticketMapper.requestToEntity(actionConfigurationRequest.getTicketTemplate())
                        : null,
                actionConfigurationRequest.getPromocodeTemplate() != null
                        ? promocodeMapper.requestToEntity(actionConfigurationRequest.getPromocodeTemplate())
                        : null,
                stageWrappers
        );
    }

    private boolean isActionConfigurationValid(ActionConfiguration actionConfiguration) {
        actionConfiguration.getStageWrappers().sort(
                ActionConfiguration.StageWrapper::compareTo
        );

        Action action = actionConfiguration.getAction();
        Ticket ticket = actionConfiguration.getTicketTemplate();
        Promocode promocode = actionConfiguration.getPromocodeTemplate();
        List<Stage> sortedStages = actionConfiguration.unwrapStages();
        return isActionTypeAndTemplateValid(action, ticket, promocode)
                && isActionAndStagesDatesEqual(action, sortedStages)
                && stageService.isStagesDatesChained(sortedStages);
    }

    private boolean isActionTypeAndTemplateValid(Action action, @Nullable Ticket ticket, @Nullable Promocode promocode) {
        return (action.getType() == ActionType.TICKET) && (ticket != null) && (promocode == null)
                || (action.getType() == ActionType.PROMOCODE) && (promocode != null) && (ticket == null);
    }

    private boolean isActionAndStagesDatesEqual(Action action, List<Stage> stages) {
        LocalDateTime actionStartDate = action.getStartDate();
        LocalDateTime actionEndDate = action.getEndDate();

        LocalDateTime firstStageStartDate;
        LocalDateTime lastStageEndDate;
        if (stages.size() == 1) {
            firstStageStartDate = stages.get(0).getStartDate();
            lastStageEndDate = stages.get(0).getEndDate();
        } else {
            firstStageStartDate = stages.get(0).getStartDate();
            lastStageEndDate = stages.get(stages.size() - 1).getEndDate();
        }

        return actionStartDate.isEqual(firstStageStartDate)
                && actionEndDate.isEqual(lastStageEndDate);
    }

    private void createTicketOrPromocode_AndSetToAction(ActionConfiguration actionConfiguration, User currentUser) {
        Ticket ticket = actionConfiguration.getTicketTemplate();
        if (ticket != null) {
            ticket = ticketService.create(ticket, currentUser);
            actionConfiguration.getAction().setTicketTemplate(ticket);
        } else {
            Promocode promocode = actionConfiguration.getPromocodeTemplate();
            promocode = promocodeService.create(promocode, currentUser);
            actionConfiguration.getAction().setPromocodeTemplate(promocode);
        }
    }

    private void createStages(ActionConfiguration actionConfiguration, User currentUser) {
        for (ActionConfiguration.StageWrapper stageWrapper : actionConfiguration.getStageWrappers()) {
            stageWrapper.getStage().setAction(actionConfiguration.getAction());
            stageWrapper.setStage(stageService.create(stageWrapper.getStage(), currentUser));
            createRules(stageWrapper, currentUser);
        }
    }

    private void createRules(ActionConfiguration.StageWrapper stageWrapper, User currentUser) {
        List<Rule> createdRules = new ArrayList<>();
        for (Rule rule : stageWrapper.getRules()) {
            rule.setStage(stageWrapper.getStage());
            createdRules.add(
                    ruleService.create(rule, currentUser)
            );
        }
        stageWrapper.setRules(createdRules);
    }

    private ActionConfigurationResponse convertActionConfigurationToResponse(ActionConfiguration actionConfiguration, User currentUser) {
        ActionResponse actionResponse = actionMapper.entityToResponse(actionConfiguration.getAction());

        List<StageConfigurationResponse> stageConfigurationResponses = new ArrayList<>();
        for (ActionConfiguration.StageWrapper stageWrapper : actionConfiguration.getStageWrappers()) {
            List<RuleResponse> ruleResponses = new ArrayList<>();
            for (Rule rule : stageWrapper.getRules()) {
                ruleResponses.add(
                        ruleMapper.entityToResponse(rule)
                );
            }
            StageConfigurationResponse stageConfigurationResponse = new StageConfigurationResponse(
                    stageMapper.entityToResponse(stageWrapper.getStage()),
                    ruleResponses
            );
            stageConfigurationResponses.add(stageConfigurationResponse);
        }

        return new ActionConfigurationResponse(
                actionResponse,
                actionConfiguration.getTicketTemplate() != null
                        ? ticketMapper.entityToResponse(actionConfiguration.getTicketTemplate())
                        : null,
                actionConfiguration.getPromocodeTemplate() != null
                        ? promocodeMapper.entityToResponse(actionConfiguration.getPromocodeTemplate())
                        : null,
                stageConfigurationResponses,
                LocalDateTime.now(),
                userMapper.entityToResponse(currentUser),
                null,
                null
        );
    }
}
