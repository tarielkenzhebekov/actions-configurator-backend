package kg.nurtelecom.internlabs.actionsconfigurator.api.service.abonentticket;


import kg.nurtelecom.internlabs.actionsconfigurator.common.entity.abonentticket.AbonentTicket;
import kg.nurtelecom.internlabs.actionsconfigurator.common.exception.MethodNotSupportedException;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.response.abonentticket.AbonentTicketResponse;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.request.abonentticket.AbonentTicketRequest;

import java.util.List;

public interface AbonentTicketService {
    List<AbonentTicketResponse> create(AbonentTicketRequest abonentTicketRequest);

    AbonentTicketResponse findById(Long id);

    default List<AbonentTicketResponse> findByPhoneNumber(String phoneNumber) throws MethodNotSupportedException {
        throw new MethodNotSupportedException();
    }

    AbonentTicket create(AbonentTicket abonentTicket);
}
