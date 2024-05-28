package kg.nurtelecom.internlabs.actionsconfigurator.common.mapper;

import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.request.action.ActionRequest;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.response.action.ActionResponse;
import kg.nurtelecom.internlabs.actionsconfigurator.common.entity.action.Action;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ActionMapper {

    @Mapping(target = "ticketTemplateId", source = "ticketTemplate.id")
    @Mapping(target = "promocodeTemplateId", source = "promocodeTemplate.id")
    @Mapping(target = "createdBy", source = "createdBy.id")
    @Mapping(target = "updatedBy", source = "updatedBy.id")
    ActionResponse entityToResponse(Action action);

    @Mapping(target = "type", source = "type")
    Action requestToEntity(ActionRequest request);
}

