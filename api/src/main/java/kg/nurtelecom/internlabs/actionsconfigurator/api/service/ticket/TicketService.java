package kg.nurtelecom.internlabs.actionsconfigurator.api.service.ticket;


import kg.nurtelecom.internlabs.actionsconfigurator.api.service.BaseService;
import kg.nurtelecom.internlabs.actionsconfigurator.api.service.EntityService;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.request.ticket.TicketRequest;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.response.ticket.TicketResponse;
import kg.nurtelecom.internlabs.actionsconfigurator.common.entity.ticket.Ticket;

/**
 * The TicketService interface provides methods for managing ticket data.
 */
public interface TicketService extends
        BaseService<TicketResponse, TicketRequest, TicketRequest, Long>,
        EntityService<Ticket, Long>
{

}
