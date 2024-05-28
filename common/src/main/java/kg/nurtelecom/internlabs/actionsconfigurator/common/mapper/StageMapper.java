package kg.nurtelecom.internlabs.actionsconfigurator.common.mapper;

import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.request.stage.StageRequest;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.response.stage.StageResponse;
import kg.nurtelecom.internlabs.actionsconfigurator.common.entity.stage.Stage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface StageMapper {

    @Mapping(target = "actionId", source = "action.id")
    @Mapping(target = "createdBy", source = "createdBy.id")
    @Mapping(target = "updatedBy", source = "updatedBy.id")
    StageResponse entityToResponse(Stage stage);

    Stage requestToEntity(StageRequest stageRequest);
}

