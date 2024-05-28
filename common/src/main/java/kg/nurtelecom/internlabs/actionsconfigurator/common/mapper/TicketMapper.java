package kg.nurtelecom.internlabs.actionsconfigurator.common.mapper;

import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.request.ticket.TicketRequest;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.response.ticket.TicketResponse;
import kg.nurtelecom.internlabs.actionsconfigurator.common.entity.ticket.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface TicketMapper {

    @Mapping(target = "ruleId", source = "rule.id")
    @Mapping(target = "createdBy", source = "createdBy.id")
    @Mapping(target = "updatedBy", source = "updatedBy.id")
    TicketResponse entityToResponse(Ticket ticket);

    Ticket requestToEntity(TicketRequest request);
}

