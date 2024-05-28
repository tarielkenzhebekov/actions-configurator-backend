package kg.nurtelecom.internlabs.actionsconfigurator.common.mapper;

import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.request.rule.RuleRequest;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.response.rule.RuleResponse;
import kg.nurtelecom.internlabs.actionsconfigurator.common.entity.rule.Rule;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface RuleMapper {

    @Mapping(target = "stageId", source = "stage.id")
    @Mapping(target = "createdBy", source = "createdBy.id")
    @Mapping(target = "updatedBy", source = "updatedBy.id")
    RuleResponse entityToResponse(Rule rule);

    Rule requestToEntity(RuleRequest ruleRequest);

    List<Rule> requestsListToEntitiesList(List<RuleRequest> ruleRequestList);
}
