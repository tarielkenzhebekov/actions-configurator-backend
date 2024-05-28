package kg.nurtelecom.internlabs.actionsconfigurator.common.mapper;


import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.request.promocode.PromocodeRequest;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.response.promocode.PromocodeResponse;
import kg.nurtelecom.internlabs.actionsconfigurator.common.entity.promocode.Promocode;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface PromocodeMapper {

    @Mapping(target = "ruleId", source = "rule.id")
    @Mapping(target = "createdBy", source = "createdBy.id")
    @Mapping(target = "updatedBy", source = "updatedBy.id")
    PromocodeResponse entityToResponse(Promocode promocode);

    Promocode requestToEntity(PromocodeRequest request);
}
