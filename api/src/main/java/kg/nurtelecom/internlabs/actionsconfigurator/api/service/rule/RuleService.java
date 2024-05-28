package kg.nurtelecom.internlabs.actionsconfigurator.api.service.rule;

import kg.nurtelecom.internlabs.actionsconfigurator.api.service.BaseService;
import kg.nurtelecom.internlabs.actionsconfigurator.api.service.EntityService;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.request.rule.RuleRequest;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.response.rule.RuleResponse;
import kg.nurtelecom.internlabs.actionsconfigurator.common.entity.rule.Rule;

import java.util.List;

/**
 * The RuleService interface provides methods for managing rule data.
 */
public interface RuleService extends
        BaseService<RuleResponse, RuleRequest, RuleRequest, Long>,
        EntityService<Rule, Long>
{

    List<RuleResponse> findAll(Double minSum, Double maxSum);

}
