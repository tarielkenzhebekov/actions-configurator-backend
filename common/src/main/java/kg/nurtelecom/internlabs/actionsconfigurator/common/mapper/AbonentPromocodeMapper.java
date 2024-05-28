package kg.nurtelecom.internlabs.actionsconfigurator.common.mapper;

import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.request.abonentpromocode.AbonentPromocodeRequest;
import kg.nurtelecom.internlabs.actionsconfigurator.common.entity.abonentpromocode.AbonentPromocode;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.response.abonentpromocode.AbonentPromocodeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface AbonentPromocodeMapper {

    @Mapping(target = "expiredDate", source = "promocode.expiredDate")
    AbonentPromocodeResponse entityToResponse(AbonentPromocode ticket);

    AbonentPromocode requestToEntity(AbonentPromocodeRequest request);
}

