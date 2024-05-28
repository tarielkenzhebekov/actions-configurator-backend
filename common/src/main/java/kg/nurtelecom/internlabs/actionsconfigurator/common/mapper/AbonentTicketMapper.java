package kg.nurtelecom.internlabs.actionsconfigurator.common.mapper;

import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.request.abonentticket.AbonentTicketRequest;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.response.abonentticket.AbonentTicketResponse;
import kg.nurtelecom.internlabs.actionsconfigurator.common.entity.abonentticket.AbonentTicket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface AbonentTicketMapper {

    @Mapping(target = "startDate", source = "ticket.startDate")
    @Mapping(target = "endDate", source = "ticket.endDate")
    AbonentTicketResponse entityToResponse(AbonentTicket ticket);

    AbonentTicket requestToEntity(AbonentTicketRequest request);
}

