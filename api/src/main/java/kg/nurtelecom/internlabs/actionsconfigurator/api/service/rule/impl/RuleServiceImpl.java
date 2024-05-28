package kg.nurtelecom.internlabs.actionsconfigurator.api.service.rule.impl;

import jakarta.annotation.Nullable;
import kg.nurtelecom.internlabs.actionsconfigurator.api.repository.rule.RuleRepository;
import kg.nurtelecom.internlabs.actionsconfigurator.api.service.rule.RuleService;
import kg.nurtelecom.internlabs.actionsconfigurator.api.service.stage.StageService;
import kg.nurtelecom.internlabs.actionsconfigurator.api.service.user.UserService;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.request.rule.RuleRequest;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.response.rule.RuleResponse;
import kg.nurtelecom.internlabs.actionsconfigurator.common.entity.rule.Rule;
import kg.nurtelecom.internlabs.actionsconfigurator.common.entity.user.User;
import kg.nurtelecom.internlabs.actionsconfigurator.common.exception.rule.RuleNotFoundException;
import kg.nurtelecom.internlabs.actionsconfigurator.common.mapper.RuleMapper;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * The RuleServiceImpl class implements the RuleService interface and provides
 * methods for managing rule data.
 */
@Service("ruleService")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@DependsOn({
        "stageService",
        "userService"
})
public class RuleServiceImpl implements RuleService {

    RuleRepository ruleRepository;

    RuleMapper ruleMapper;

    StageService stageService;

    UserService userService;

    @Override
    public RuleResponse create(@NonNull RuleRequest ruleRequest) {
        Rule rule = mapToRule_AndSetStage(ruleRequest);

        return ruleMapper.entityToResponse(
                create(
                        rule,
                        userService.getCurrentUser()
                )
        );
    }

    @Override
    public RuleResponse findById(@NonNull Long id) {
        return ruleMapper.entityToResponse(
                find(id)
        );
    }

    @Override
    public List<RuleResponse> findAll() {
        return ruleRepository.findAll()
                .stream()
                .map(ruleMapper::entityToResponse)
                .toList();
    }

    @Override
    public RuleResponse updateById(@NonNull Long id, @NonNull RuleRequest ruleRequest) {
        Rule rule = mapToRule_AndSetStage(ruleRequest);

        return ruleMapper.entityToResponse(
                update(
                        id,
                        rule,
                        userService.getCurrentUser()
                )
        );
    }

    @Override
    public RuleResponse deleteById(@NonNull Long id) {
        return ruleMapper.entityToResponse(
                delete(
                        id,
                        userService.getCurrentUser()
                )
        );
    }

    @Override
    public Long count() {
        return ruleRepository.count();
    }

    @Override
    public Boolean existsById(@NonNull Long id) {
        return ruleRepository.existsById(id);
    }


    @Override
    public Rule create(@NonNull Rule rule, @Nullable User currentUser) {
        rule.setCreatedBy(currentUser);
        rule.setCreatedAt(LocalDateTime.now());

        return ruleRepository.save(rule);
    }

    @Override
    public Rule find(@NonNull Long id) {
        Optional<Rule> byId = ruleRepository.findById(id);

        if (byId.isPresent()) {
            return byId.get();
        } else {
            throw new RuleNotFoundException("Cannot find user by provided id: " + id);
        }
    }

    @Override
    public Rule update(@NonNull Long ruleId, @NonNull Rule updatedRule, @Nullable User currentUser) {
        Rule rule = find(ruleId);

        rule.setMinSum(updatedRule.getMinSum());
        rule.setMaxSum(updatedRule.getMaxSum());
        rule.setAmount(updatedRule.getAmount());
        rule.setStage(updatedRule.getStage());

        rule.setUpdatedBy(currentUser);
        rule.setUpdatedAt(LocalDateTime.now());

        return ruleRepository.save(rule);
    }

    @Override
    public Rule delete(@NonNull Long ruleId, @Nullable User currentUser) {
        Rule rule = find(ruleId);

        rule.setDeleted(true);
        rule.setUpdatedBy(currentUser);
        rule.setUpdatedAt(LocalDateTime.now());

        return rule;
    }

    /**
     * Returns list of rules. If minSum and maxSum is not null then will return
     * list of elements by minSum and maxSum. If either one of sums null then
     * will return by not null sum. And if all sums are null than will return
     * list of all elements.
     * @param minSum Minimal sum in entity.
     * @param maxSum Maximal sum in entity,
     * @return List of elements by min, max or all.
     */
    @Override
    public List<RuleResponse> findAll(Double minSum, Double maxSum) {
        if (minSum != null && !Double.isInfinite(minSum) && !Double.isNaN(minSum)) {
            if (maxSum != null && !Double.isInfinite(maxSum) && !Double.isNaN(maxSum)) {
                return ruleRepository.findAllByMinSumAndMaxSum(minSum, maxSum)
                        .stream()
                        .map(ruleMapper::entityToResponse)
                        .toList();
            }
            return ruleRepository.findAllByMinSum(minSum)
                    .stream()
                    .map(ruleMapper::entityToResponse)
                    .toList();
        } else if (maxSum != null && !Double.isInfinite(maxSum) && !Double.isNaN(maxSum)) {
            return ruleRepository.findAllByMinSum(maxSum)
                    .stream()
                    .map(ruleMapper::entityToResponse)
                    .toList();
        }
        return ruleRepository.findAll()
                    .stream()
                    .map(ruleMapper::entityToResponse)
                    .toList();
    }

    private Rule mapToRule_AndSetStage(RuleRequest ruleRequest) {
        Rule rule = ruleMapper.requestToEntity(ruleRequest);

        if (ruleRequest.stageId() != null) {
            rule.setStage(
                    stageService.find(ruleRequest.stageId())
            );
        }

        return rule;
    }
}
